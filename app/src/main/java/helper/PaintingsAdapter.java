package helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;
import com.bumptech.glide.Glide;
import com.tech.dota.pearl2016.Headliners;
import com.tech.dota.pearl2016.R;

import java.util.Arrays;

public class PaintingsAdapter extends ItemsAdapter<Painting> implements View.OnClickListener {

    public PaintingsAdapter(Context context) {
        super(context);
        setItemsList(Arrays.asList(Painting.getAllPaintings(context.getResources())));
    }

    @Override
    protected View createView(Painting item, int pos, ViewGroup parent, LayoutInflater inflater) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headliner_listitem, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.image = Views.find(view, R.id.list_item_image);
        vh.image.setOnClickListener(this);
        vh.title = Views.find(view, R.id.list_item_title);
        view.setTag(vh);

        return view;
    }

    @Override
    protected void bindView(Painting item, int pos, View convertView) {
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.image.setTag(R.id.list_item_image, item);
        Glide.with(convertView.getContext())
                .load(item.getImageId())
                .dontTransform()
                .dontAnimate()
                .into(vh.image);
        vh.title.setText("");
    }

    @Override
    public void onClick(View view) {
        Painting item = (Painting) view.getTag(R.id.list_item_image);
        if (view.getContext() instanceof Headliners) {
            ((Headliners) view.getContext()).openDetails(view, item);
        }
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

}
