package com.example.calendariourjc.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.calendariourjc.CalendarActivity
import com.example.calendariourjc.EventsActivity
import com.example.calendariourjc.ScheduleActivity
import com.example.calendariourjc.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val calendarButton = binding.calendarButton;
        calendarButton.setOnClickListener{
            val intent = Intent(context, CalendarActivity::class.java)
            startActivity(intent)
        }

        val scheduleBtn = binding.scheduleBtn;
        scheduleBtn.setOnClickListener {
            val scheduleActivity = Intent(context, ScheduleActivity::class.java)
            startActivity(scheduleActivity)
        }

        val goEventsBtn = binding.eventsPageGo;
        goEventsBtn.setOnClickListener {
            val eventsActivity = Intent(context, EventsActivity::class.java)
            startActivity(eventsActivity)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}