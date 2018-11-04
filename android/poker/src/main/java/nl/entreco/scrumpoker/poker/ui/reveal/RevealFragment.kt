package nl.entreco.scrumpoker.poker.ui.reveal

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import nl.entreco.scrumpoker.poker.R
import nl.entreco.scrumpoker.poker.databinding.FragmentRevealBinding
import nl.entreco.scrumpoker.poker.ui.poker.stack.StackAnimator

class RevealFragment : Fragment() {

    private lateinit var binding: FragmentRevealBinding
    private val adapter by lazy {
        RevealAdapter(arguments?.getString(ARG_VALUE, "") ?: "", reveal = revealCard(), dismiss = dismissCard())
    }

    private fun revealCard(): (View) -> Unit {
        return {
            binding.revealPager.currentItem += 1
        }
    }

    private fun dismissCard(): (View) -> Unit {
        return { view ->
            view.animate()
                .scaleX(.7F).scaleY(.7F)
                .translationX(0F)
                .translationY(0F)
                .alpha(0F).withEndAction {
                    doDismiss()
                }.setDuration(200).start()
        }
    }

    private fun doDismiss() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitNow()
    }

    private val transformer by lazy { RevealTransformer() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reveal, container, false)
        prepareSharedElementTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.revealPager.adapter = adapter
        binding.revealPager.setPageTransformer(false, transformer)
        val overscrolHelper = OverScrollDecoratorHelper.setUpOverScroll(binding.revealPager)
        overscrolHelper.setOverScrollUpdateListener { decor, state, offset ->
            Log.i("HELP", "offset: $offset")


            val newX: Float = offset
            val newY: Float = 0F

            val rotationX = (newY) / 1500 * 10
            val rotationY = 0F

            view.animate()
                .x(newX)
                .y(newY)
                .rotationX(rotationX)
                .rotationY(rotationY)
                .setDuration(0)
                .start()


            when (offset <= -150) {
                true -> {
                    overscrolHelper.setOverScrollUpdateListener(null)
                    view.animate()
                        .scaleX(.7F).scaleY(.7F)
                        .translationX(0F)
                        .translationY(0F)
                        .alpha(0F).withEndAction {
                        doDismiss()
                    }.setDuration(200).start()
                }
            }
        }
    }

    /**
     * Prepares the shared element transition from and back to the grid fragment.
     */
    private fun prepareSharedElementTransition() {
        val transition = TransitionInflater.from(context).inflateTransition(R.transition.shared_enter_transition)
        sharedElementEnterTransition = transition
    }

    companion object {
        private const val ARG_VALUE = "arg_value"
        fun newInstance(value: String): RevealFragment {
            val frag = RevealFragment()
            val bundle = Bundle()
            bundle.putString(ARG_VALUE, value)
            frag.arguments = bundle
            return frag
        }
    }
}