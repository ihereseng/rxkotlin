package com.example.rxkotlin

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.example.rxkotlin.databinding.ActivityMainBinding
import com.example.rxkotlin.model.Result
import com.example.rxkotlin.util.Converter
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity(), IMainPresenter.View {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUserToTable()
    }

    override fun setUserToTable() {

        MainPresenter().getUser()
            .doOnSubscribe {
                binding.progressBar.visibility = View.VISIBLE
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    binding.progressBar.visibility = View.GONE
                    it.results?.let { it1 ->
                        createAndSetUserToTableRow(it1)
                        createTableHeader()
                    }
                },
                onError = {
                    println("Error get user $it")
                    createErrorUser("Something bad happen please try again later")
                }
            )
    }

    private fun createTableHeader() {
        val row = TableRow(this)
        row.layoutParams =
            TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 0)
        row.gravity = Gravity.CENTER_VERTICAL
        val imgHeaderView = TextView(this)
        val nameHeaderView = TextView(this)
        val ageHeaderView = TextView(this)
        val emailHeaderView = TextView(this)

        imgHeaderView.text = "Picture"
        nameHeaderView.text = "Name"
        ageHeaderView.text = "Age"
        emailHeaderView.text = "Email"

        imgHeaderView.setPadding(Converter.dpToPixel(15, this))
        nameHeaderView.setPadding(Converter.dpToPixel(15, this))
        ageHeaderView.setPadding(Converter.dpToPixel(15, this))
        emailHeaderView.setPadding(Converter.dpToPixel(15, this))

        // TODO: 28/5/2021 AD
        // Implement responsive header

        row.addView(imgHeaderView)
        row.addView(nameHeaderView)
        row.addView(ageHeaderView)
        row.addView(emailHeaderView)
        binding.tableHeader.addView(row)
    }

    private fun createAndSetUserToTableRow(results: List<Result>) {
        for (result in results) {
            val row = TableRow(this)
            row.layoutParams =
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 0)
            row.gravity = Gravity.CENTER_VERTICAL
            val imgView = ImageView(this)
            val nameTextView = TextView(this)
            val ageTextView = TextView(this)
            val emailTextView = TextView(this)

            Picasso.get().load(result.picture?.large).into(imgView)
            nameTextView.text =
                result.name?.title + " " + result.name?.first + " " + result.name?.last
            ageTextView.text = result.dob.age.toString()
            emailTextView.text = result.email.toString()

            imgView.setPadding(Converter.dpToPixel(15, this))
            nameTextView.setPadding(Converter.dpToPixel(15, this))
            ageTextView.setPadding(Converter.dpToPixel(15, this))
            emailTextView.setPadding(Converter.dpToPixel(15, this))

            row.addView(imgView)
            row.addView(nameTextView)
            row.addView(ageTextView)
            row.addView(emailTextView)
            binding.table.addView(row)
        }
    }

    private fun createErrorUser(errorText: String) {
        val errorTextView = TextView(this)
        errorTextView.text = errorText
        val h = RelativeLayout.LayoutParams.MATCH_PARENT
        val w = RelativeLayout.LayoutParams.MATCH_PARENT
        errorTextView.gravity = Gravity.CENTER
        errorTextView.layoutParams = ConstraintLayout.LayoutParams(w, h)
        binding.parent.addView(errorTextView)
    }

}