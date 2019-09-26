package by.morozova.people;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.morozova.people.database.entity.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PeopleHolder> {

	List<Person> data = new ArrayList<>();

	@NonNull
	@Override
	public PeopleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
		return new PeopleHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PeopleHolder holder, int position) {
		holder.bind(data.get(position));
	}

	@Override
	public int getItemCount() {
		return data.size();
	}

	public void setData(List<Person> people) {
		data.clear();
		data.addAll(people);
		notifyDataSetChanged();
	}

	static class PeopleHolder extends RecyclerView.ViewHolder {

		private TextView id;
		private TextView name;
		private ImageView sex;
		private TextView phone;
		private TextView city;

		PeopleHolder(View itemView) {
			super(itemView);
			id =  itemView.findViewById(R.id.tv_id);
			name =  itemView.findViewById(R.id.tv_name);
			sex =  itemView.findViewById(R.id.iv_sex);
			phone =  itemView.findViewById(R.id.tv_phone);
			city =  itemView.findViewById(R.id.tv_city);
		}

		void bind(Person people) {
//			setText(String.format("id: %s, name: %s, email: %s", people.getId(), people.getName(), people.getPhone()));
			id.setText(String.valueOf(people.getId()));
			name.setText(people.getName());
			phone.setText(people.getPhone());
			city.setText(people.getCity());
			if(people.isSexMan()){
				sex.setImageDrawable(itemView.getResources().getDrawable(R.drawable.man));
			} else {
				sex.setImageDrawable(itemView.getResources().getDrawable(R.drawable.woman));
			}
		}
	}

}
