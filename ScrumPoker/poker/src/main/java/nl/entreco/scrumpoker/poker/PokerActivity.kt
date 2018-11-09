package nl.entreco.scrumpoker.poker

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.mcxtzhang.layoutmanager.swipecard.CardConfig
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager
import com.mcxtzhang.layoutmanager.swipecard.RenRenCallback
import nl.entreco.scrumpoker.BaseActivity
import nl.entreco.scrumpoker.feature_poker.R
import nl.entreco.scrumpoker.feature_poker.databinding.ActivityPokerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules



class PokerActivity : BaseActivity() {

    val pokerModule = module {
        viewModel { PokerViewModel() }
    }

    private lateinit var binding: ActivityPokerBinding
    private val vm: PokerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(pokerModule)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poker)
        binding.vm = vm
    }

    override fun onResume() {
        super.onResume()
//        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
//        binding.recyclerView.layoutManager = StackLayoutManager()

        val adapter = CardsAdapter()

        CardConfig.initConfig(this)
        CardConfig.MAX_SHOW_COUNT = 15;
        binding.recyclerView.layoutManager = OverLayCardLayoutManager()
        val callback = RenRenCallback(binding.recyclerView, adapter, vm.deck())
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.adapter = adapter
        vm.cards().observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
