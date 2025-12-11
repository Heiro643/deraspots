// SpotAdapter.java
package jp.ac.meijou.android.deraspots;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jp.ac.meijou.android.deraspots.databinding.ListItemBinding;

public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.SpotViewHolder> {

    private final List<Spot> spotList;

    public SpotAdapter(List<Spot> spotList) {
        this.spotList = spotList;
    }

    @NonNull
    @Override
    public SpotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ViewHolderを作成
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SpotViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotViewHolder holder, int position) {
        // ViewHolderにデータをバインド
        Spot spot = spotList.get(position);
        holder.bind(spot);
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }

    // ViewHolderクラス
    static class SpotViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public SpotViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Spot spot) {
            binding.itemTextTitle.setText(spot.title);
            binding.itemTextExplain.setText(spot.description);
            binding.itemImage.setImageResource(spot.imageResource);
            binding.itemButton.setOnClickListener(v -> {
                spot.startDestinationActivity(v.getContext());
            });
        }
    }
}
