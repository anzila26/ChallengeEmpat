package anzila.binar.challengeempat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anzila.binar.challengeempat.databinding.ItemNoteBinding
import anzila.binar.challengeempat.room.NoteData
import anzila.binar.challengeempat.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class  NoteAdapter(var listNote : List<NoteData>, val listener:NoteAdapterListener): RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    class ViewHolder(var binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)
    fun setNewData(listNote:List<NoteData>){
        this.listNote = listNote
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtIsi.text = listNote[position].content
        holder.binding.txtJudul.text = listNote[position].title
        holder.binding.txtId.text = listNote[position].id.toString()
        holder.binding.btnDelete.setOnClickListener{
            listener.deleteClick(listNote[position])
        }

        holder.binding.btnEdit.setOnClickListener {
            listener.editClick(listNote[position])
        }
    }

    override fun getItemCount(): Int {
        return  listNote.size
    }

    interface NoteAdapterListener{
        fun editClick(note:NoteData)
        fun deleteClick(note:NoteData)
    }
}