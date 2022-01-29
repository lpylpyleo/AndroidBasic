package com.example.androidbasic.ui.main

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidbasic.AndroidBasicApplication
import com.example.androidbasic.ui.player.PlayerActivity
import com.example.androidbasic.R
import com.example.androidbasic.data.model.Video
import com.example.androidbasic.databinding.FragmentTabBinding
import com.example.androidbasic.databinding.VideoViewItemBinding
import com.example.androidbasic.util.ObjectWrapperBinder
import com.example.androidbasic.util.Util


class PlaceholderFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentTabBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel =
            PageViewModel((activity?.application as AndroidBasicApplication).videoRepository).apply {
                setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        val root = binding.root

        val recyclerView = binding.recyclerView
        val adapter = HomePageRecyclerViewAdaptor()

        recyclerView.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this.context, 2)
            addItemDecoration(
                GridSpacingItemDecoration(
                    2,
                    Util.dpToPx(8.0f, requireContext())
                )
            )
        }

        pageViewModel.videos.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        pageViewModel.getRecommend()

        return root
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class HomePageRecyclerViewAdaptor :
    ListAdapter<Video, HomePageRecyclerViewAdaptor.HomePageRecyclerViewHolder>(VideoDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageRecyclerViewHolder {
        return HomePageRecyclerViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomePageRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomePageRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = VideoViewItemBinding.bind(itemView)

        fun bind(video: Video) {
            binding.apply {
                title.text = video.title
                tag.text = video.tname
                category.text = video.owner.name
                Glide.with(itemView).load(video.pic).into(image)
                root.setOnClickListener {
                    val intent = Intent(itemView.context, PlayerActivity::class.java).apply {
                        val bundle = Bundle()
                        bundle.putBinder(PlayerActivity.ARG_VIDEO_BVID, ObjectWrapperBinder(video))
                        putExtras(bundle)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }


        companion object {
            fun create(parent: ViewGroup): HomePageRecyclerViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_view_item, parent, false)
                return HomePageRecyclerViewHolder(view)
            }
        }
    }

    class VideoDiff : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
    }


}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        val column = itemPosition % spanCount

        outRect.left = column * spacing / spanCount
        outRect.right = spacing - (column + 1) * spacing / spanCount
        if (itemPosition >= spanCount) {
            outRect.top = spacing // item top
        }
    }
}

