package nl.entreco.scrumpoker.poker.select

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import nl.entreco.scrumpoker.BaseActivity
import nl.entreco.scrumpoker.GridItemDecorator
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.ActivityPokerBinding
import nl.entreco.scrumpoker.poker.select.stack.StackCardTouchHelperCallback
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.android.ext.android.inject
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import nl.entreco.scrumpoker.poker.select.carousel.CarouselLayoutManager
import nl.entreco.scrumpoker.poker.select.carousel.CarouselZoomPostLayoutListener
import nl.entreco.scrumpoker.poker.select.carousel.CenterScrollListener

class SelectCardActivity : BaseActivity(), OnStartDragListener {
    val pokerModule = module {
        viewModel { SelectCardViewModel() }
        single { CardsAdapter() }
    }

    private lateinit var binding: ActivityPokerBinding

    private val viewModel: SelectCardViewModel by viewModel()
    private val cardsAdapter: CardsAdapter by inject()
    private var itemTouchHelper: ItemTouchHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(pokerModule)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poker)
        binding.vm = viewModel

        viewModel.toggle().observe(this, Observer{
            if(it){
                setupGridView()
            } else {
                setupStackView()
            }
        })

        viewModel.cards().observe(this, Observer {
            cardsAdapter.submitList(it)
        })
    }

    private fun setupGridView() {
        binding.stackView.visibility = View.GONE
        cardsAdapter.onDragListener = null

        binding.gridView.apply {
            adapter = cardsAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@SelectCardActivity, 3)
            addItemDecoration(GridItemDecorator(this@SelectCardActivity, R.dimen.grid_spacing))
            visibility = View.VISIBLE
        }
    }

    private fun setupStackView() {
        binding.gridView.visibility = View.GONE

        binding.stackView.apply {
            adapter = cardsAdapter
            setHasFixedSize(true)
//            layoutManager = StackLayoutManager(this@SelectCardActivity, RecyclerView.VERTICAL, true)
            layoutManager = CarouselLayoutManager(
                RecyclerView.VERTICAL,
                true
            ).apply {
                setPostLayoutListener(CarouselZoomPostLayoutListener())
            }
            addOnScrollListener(CenterScrollListener())
            addItemDecoration(GridItemDecorator(this@SelectCardActivity, R.dimen.grid_spacing))
            visibility = View.VISIBLE
        }

        val callback = StackCardTouchHelperCallback(cardsAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper?.attachToRecyclerView(binding.stackView)

        cardsAdapter.onDragListener = this
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        // Start Drag
        Log.i("REMCO", "startDrag: $viewHolder")
    }
}
