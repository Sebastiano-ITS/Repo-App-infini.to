package com.example.infinito.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.infinito.R
import com.example.infinito.data.model.User
import com.example.infinito.ui.login.LoginActivity
import com.example.infinito.utils.UserUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val SURNAME = "surname"
private const val EMAIL = "email"
private const val PASSWORD = "password"
private const val ISLOGGEDIN = "isLoggedIn"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfirmDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfirmDialogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = User(
                it.getString(NAME)!!,
                it.getString(SURNAME)!!,
                it.getString(EMAIL)!!,
                it.getString(PASSWORD)!!,
                it.getBoolean(ISLOGGEDIN)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val yesBtn = view.findViewById<Button>(R.id.yesBtn)

        yesBtn.setOnClickListener {
            val indexToRemove = UserUtils.getUsers(view.context).indexOfFirst { it == user }
            val users = UserUtils.getUsers(view.context).toMutableList()
            users.removeAt(indexToRemove)
            UserUtils.saveUsers(view.context, users)
            val loginIntent = Intent(view.context, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(loginIntent)
        }

        val noBtn = view.findViewById<Button>(R.id.noBtn)

        noBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param user User.
         * @return A new instance of fragment ConfirmDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(user: User) =
            ConfirmDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("name", user.name)
                    putString("surname", user.surname)
                    putString("email", user.email)
                    putString("password", user.password)
                    putBoolean("isLoggedIn", user.isLoggedIn)
                }
            }
    }
}