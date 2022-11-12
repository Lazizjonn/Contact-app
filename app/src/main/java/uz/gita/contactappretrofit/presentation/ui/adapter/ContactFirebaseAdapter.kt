package uz.gita.contactappretrofit.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappretrofit.data.model.firebase.ContactDataFireBase
import uz.gita.contactappretrofit.data.model.response.ContactResponse
import uz.gita.contactappretrofit.data.source.local.room.entity.ContactData
import uz.gita.contactappretrofit.databinding.ItemContactBinding


class ContactFirebaseAdapter : ListAdapter<ContactDataFireBase, ContactFirebaseAdapter.ContactViewHolder>(ContactDiffUtil) {
    private var selectContactDataFireBaseListener: ((ContactDataFireBase) -> Unit)? = null

    object ContactDiffUtil : DiffUtil.ItemCallback<ContactDataFireBase>() {
        override fun areItemsTheSame(oldItem: ContactDataFireBase, newItem: ContactDataFireBase): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ContactDataFireBase, newItem: ContactDataFireBase): Boolean {
            return oldItem == newItem
        }
    }
    inner class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonMore.setOnClickListener {
                selectContactDataFireBaseListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.textName.text = "$lastName $firstName"
                binding.textNumber.text = phone
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }

    fun setSelectContactDataListener(block: (ContactDataFireBase) -> Unit) {
        selectContactDataFireBaseListener = block
    }
}
