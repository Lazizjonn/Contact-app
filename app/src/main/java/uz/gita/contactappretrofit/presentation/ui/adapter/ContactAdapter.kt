package uz.gita.contactappretrofit.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappretrofit.data.remote.data.response.ContactResponse
import uz.gita.contactappretrofit.databinding.ItemContactBinding


class ContactAdapter : ListAdapter<ContactResponse, ContactAdapter.ContactViewHolder>(ContactDiffUtil) {
    private var selectContactResponseListener: ((ContactResponse) -> Unit)? = null

    object ContactDiffUtil : DiffUtil.ItemCallback<ContactResponse>() {
        override fun areItemsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactResponse, newItem: ContactResponse): Boolean {
            return oldItem == newItem
        }
    }
    inner class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.buttonMore.setOnClickListener {
                selectContactResponseListener?.invoke(getItem(absoluteAdapterPosition))
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

    fun setSelectContactDataListener(block: (ContactResponse) -> Unit) {
        selectContactResponseListener = block
    }
}
