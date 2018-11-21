package nl.entreco.scrumpoker.poker.select

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import nl.entreco.scrumpoker.BaseActivity
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.ActivityPokerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import java.util.concurrent.atomic.AtomicBoolean
import org.koin.android.ext.android.inject

class SelectCardActivity : BaseActivity() {

    val pokerModule = module {
        viewModel { SelectCardViewModel() }
        single { CardsAdapter() }
    }

    private val showStack = AtomicBoolean(false)
    private lateinit var binding: ActivityPokerBinding
    private val viewModel: SelectCardViewModel by viewModel()
    private val adapter: CardsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(pokerModule)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_poker)
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
//        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.HORIZONTAL))
        viewModel.cards().observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
