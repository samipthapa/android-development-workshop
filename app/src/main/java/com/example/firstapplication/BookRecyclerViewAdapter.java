package com.example.firstapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import static com.example.firstapplication.DetailBookActivity.BOOK_ID_KEY;

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "BookRecyclerViewAdapter";

    private ArrayList<Book> books = new ArrayList<>();
    private String parentActivity;
    private Context context;

    public BookRecyclerViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.textName.setText(books.get(position).getName());
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imageBook);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, books.get(position).getName() + " Selected", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DetailBookActivity.class);
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.textAuthor.setText(books.get(position).getAuthor());
        holder.textShortDescription.setText(books.get(position).getShortDesc());

        if(books.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
            if (parentActivity.equals("allBooks")) {
                holder.delete.setVisibility(View.GONE);
            }
            else if (parentActivity.equals("alreadyReadBooks")) {
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(context).removeFromAlreadyReadBooks(books.get(position))) {
                                    Toast.makeText(context, books.get(position).getName() + " Removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();

                                    //reloads the already read activity
                                    Intent intent = new Intent(context, AlreadyReadBookActivity.class);
                                    context.startActivity(intent);
                                }
                                else {
                                    Toast.makeText(context, "Error! Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
            else if (parentActivity.equals("currentlyReadingBooks")) {
                holder.delete.setVisibility(View.VISIBLE);
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + books.get(position).getName() + "?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(context).removeFromCurrentlyReading(books.get(position))) {
                                    Toast.makeText(context, books.get(position).getName() + " Removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();

                                    //reloads the currently reading books activity
                                    Intent intent = new Intent(context, CurrentlyReadingBookActivity.class);
                                    context.startActivity(intent);
                                }
                                else {
                                    Toast.makeText(context, "Error! Try Again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });
            }
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private ImageView imageBook;
        private TextView textName;
        private ImageView downArrow, upArrow;
        private TextView textAuthor, textShortDescription;
        private RelativeLayout expandedRelLayout;
        private TextView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.bookCardViewParent);
            imageBook = itemView.findViewById(R.id.imgBook);
            textName = itemView.findViewById(R.id.textBookName);

            downArrow = itemView.findViewById(R.id.expandCardButton);
            upArrow = itemView.findViewById(R.id.CollapseCardButton);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelativeLayout);
            textAuthor = itemView.findViewById(R.id.authorTextValue);
            textShortDescription = itemView.findViewById(R.id.textShortDescriptionValue);
            delete = itemView.findViewById(R.id.deleteText);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());

                    //reverses the current Boolean value of isExpanded
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
