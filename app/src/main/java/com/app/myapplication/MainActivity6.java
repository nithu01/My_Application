package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity6 extends AppCompatActivity {
    Integer[] data={1,2,3,4,5,6,7,8,9};
    CompositeDisposable disposable=new CompositeDisposable();
    private Apiinterface apiService;
    private TicketAdapter mAdapter;
    private ArrayList<Ticket> ticketsList = new ArrayList<>();
    RecyclerView recyclerView;
    private static final String from = "DEL";
    private static final String to = "HYD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        recyclerView=findViewById(R.id.recycler_view);
        mAdapter = new TicketAdapter(this, ticketsList, (TicketAdapter.TicketsAdapterListener) this);
        apiService = ApiClient.getClient().create(Apiinterface.class);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        ConnectableObservable<List<Ticket>> ticketsObservable = getTickets(from, to).replay();

//        Observable.just(1,2,3,4,5,6,7,8)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.io())
//                .buffer(2)
//                .subscribe(new Observer<List<Integer>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Integer> integers) {
//                        Log.d("TAG", "onNext");
//                        for (Integer integer : integers) {
//                            Log.d("TAG", "Item: " + integer);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("TAG", "All items emitted!");
//                    }
//                });
        disposable.add(
                ticketsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<Ticket>>() {

                            @Override
                            public void onNext(List<Ticket> tickets) {
                                // Refreshing list
                                ticketsList.clear();
                                ticketsList.addAll(tickets);
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                //showError(e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));


        disposable.add(
                ticketsObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        /**
                         * Converting List<Ticket> emission to single Ticket emissions
                         * */
                        .flatMap(new Function<List<Ticket>, ObservableSource<Ticket>>() {
                            @Override
                            public ObservableSource<Ticket> apply(List<Ticket> tickets) throws Exception {
                                return Observable.fromIterable(tickets);
                            }
                        })
                        /**
                         * Fetching price on each Ticket emission
                         * */
                        .flatMap(new Function<Ticket, ObservableSource<Ticket>>() {
                            @Override
                            public ObservableSource<Ticket> apply(Ticket ticket) throws Exception {
                                return getPriceObservable(ticket);
                            }
                        })
                        .subscribeWith(new DisposableObserver<Ticket>() {

                            @Override
                            public void onNext(Ticket ticket) {
                                int position = ticketsList.indexOf(ticket);

                                if (position == -1) {
                                    // TODO - take action
                                    // Ticket not found in the list
                                    // This shouldn't happen
                                    return;
                                }

                                ticketsList.set(position, ticket);
                                mAdapter.notifyItemChanged(position);
                            }

                            @Override
                            public void onError(Throwable e) {
                                //showError(e);
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));

        // Calling connect to start emission
        ticketsObservable.connect();

        Observable<Note> observable=getObservable();
        DisposableObserver<Note> observer=getNotesObserver();
        observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    private Observable<Ticket> getPriceObservable(final Ticket ticket) {
        return apiService
                .getPrice(ticket.getFlightNumber(), ticket.getFrom(), ticket.getTo())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Price, Ticket>() {
                    @Override
                    public Ticket apply(Price price) throws Exception {
                        ticket.setPrice(price);
                        return ticket;
                    }
                });
    }

    public Observable<List<Ticket>> getTickets(String from,String to){
        return apiService.searchTickets(from,to)
                .toObservable()
                .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<Note> getNotesObserver(){
        return new DisposableObserver<Note> (){

            @Override
            public void onNext(@NonNull Note note) {
                Log.d("TAG","Valuedata"+note.getNote());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private Observable<Note> getObservable(){
        List<Note> list=getallnotes();
        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Note> emitter) throws Exception {
                for( Note note : list){
                    if(!emitter.isDisposed()){
                        emitter.onNext(note);
                    }
                }
            }
        });
    }
    private List<Note> getallnotes(){
        List<Note> notes=new ArrayList<>();
        notes.add(new Note(1, "Buy tooth paste!"));
        notes.add(new Note(2, "Call brother!"));
        notes.add(new Note(3, "Call brother!"));
        notes.add(new Note(4, "Pay power bill!"));
        notes.add(new Note(5, "Watch Narcos tonight!"));
        notes.add(new Note(6, "Buy tooth paste!"));
        notes.add(new Note(7, "Pay power bill!"));

        return notes;
    }
    public class Note{
        int id;
        String note;

        public Note(int id, String note) {
            this.id = id;
            this.note = note;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Note)) {
                return false;
            }

            return note.equalsIgnoreCase(((Note) obj).getNote());
        }
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + (this.note != null ? this.note.hashCode() : 0);
            return hash;
        }
    }
//
//    @Override
//    public void onTicketSelected(Ticket contact) {
//
//    }
}
