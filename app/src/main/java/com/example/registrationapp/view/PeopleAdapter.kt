package com.example.registrationapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.registrationapp.R
import com.example.registrationapp.databinding.ItemPeopleBinding
import com.example.registrationapp.model.People
import com.example.registrationapp.viewmodel.ItemPeopleViewModel

class PeopleAdapter internal constructor() : RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {

    private var peopleList: List<People>? = null

    init {
        this.peopleList = emptyList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapterViewHolder {
        val itemPeopleBinding:ItemPeopleBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.item_people, parent,false);
        return PeopleAdapterViewHolder(itemPeopleBinding)
    }

    override fun onBindViewHolder(holder: PeopleAdapterViewHolder, position: Int) {
        holder.bindPeople(peopleList!![position])
    }

    override fun getItemCount(): Int {
        return peopleList!!.size
    }

    internal fun setPeopleList(peopleList: List<People>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    class PeopleAdapterViewHolder(var mItemPeopleBinding: ItemPeopleBinding) : RecyclerView.ViewHolder(mItemPeopleBinding.itemPeople) {

        fun bindPeople(people: People) {
            if (mItemPeopleBinding.peopleViewModel == null) {
                mItemPeopleBinding.peopleViewModel = ItemPeopleViewModel(people, itemView.context)
            } else {
                mItemPeopleBinding.peopleViewModel!!.setPeople(people)
            }
        }
    }
}