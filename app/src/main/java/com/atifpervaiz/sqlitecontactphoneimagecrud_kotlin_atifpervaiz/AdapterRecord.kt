package com.atifpervaiz.sqlitecontactphoneimagecrud_kotlin_atifpervaiz

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for recyclerView
class AdapterRecord(): RecyclerView.Adapter<AdapterRecord.HolderRecord>(){

    private var context:Context?=null
    private var recordList:ArrayList<ModelRecord>?=null

    constructor(context: Context?, recordList: ArrayList<ModelRecord>?) : this() {
        this.context = context
        this.recordList = recordList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRecord {
        // inflate the layout row_record.xml
        return HolderRecord(
            LayoutInflater.from(context).inflate(R.layout.row_record, parent, false)
        )
    }

    override fun getItemCount(): Int {
        // return items/records/list size
        return recordList!!.size
    }

    override fun onBindViewHolder(holder: HolderRecord, position: Int) {
        // get data, set data, handle clicks

        //get data
        val model = recordList!!.get(position)

        val id = model.id
        val name = model.name
        val image = model.image
        val bio = model.bio
        val phone = model.phone
        val email = model.email
        val dob = model.dob
        val addedTime = model.addedTime
        val updatedTime = model.updatedTime

        // set data to views
        holder.nameIv.text = name
        holder.phoneTv.text = phone
        holder.emailTv.text = email
        holder.dobTv.text = dob
        // if user dosn't attach image then imageUri will be null, so set default image in that case
        if (image == "null"){
            // no image in record, set default
            holder.profileIv.setImageResource(R.drawable.ic_person_black)
        }else{
            // have image in record
            holder.profileIv.setImageURI(Uri.parse(image))
        }

        // show record in new activity on clicking record
        holder.itemView.setOnClickListener {
            // pass id to next activity to show record
            val intent = Intent(context, RecordDetailActivity::class.java)
            intent.putExtra("RECORD_ID", id)
            context!!.startActivity(intent)
        }

        // handle more button click: show delete/edit options
        holder.morebtn.setOnClickListener {
            // will implement later
        }

    }

    inner class HolderRecord(itemView: View): RecyclerView.ViewHolder(itemView) {

        // Views from Row_record.xml
        var profileIv:ImageView = itemView.findViewById(R.id.profileIv)
        var nameIv:TextView = itemView.findViewById(R.id.nameTv)
        var phoneTv:TextView = itemView.findViewById(R.id.phoneTv)
        var emailTv:TextView = itemView.findViewById(R.id.emailTv)
        var dobTv:TextView = itemView.findViewById(R.id.dobTv)
        var morebtn:ImageButton = itemView.findViewById(R.id.moreBtn)

    }


}