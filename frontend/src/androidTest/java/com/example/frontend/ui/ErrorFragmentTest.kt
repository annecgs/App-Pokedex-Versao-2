package com.example.frontend.ui

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bumptech.glide.Glide
import com.example.frontend.ui.error.ErrorFragment
import com.example.frontend.R
import kotlinx.android.synthetic.main.fragment_error.*
import kotlinx.android.synthetic.main.fragment_error.view.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorFragmentTest {
    @Test
    fun Error400Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_400)
                    .centerCrop()
                    .into(this.imagemError)
            }
            this.mensagemError.text = "Instabilidade no seu computador ou na sua conexão de Internet"


        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun Error401Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "O site que você está tentando acessar se encontra protegido e requer autorização ou autenticação"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_401)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun Error403Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Negação por parte do proprietário, que não permite que a página receba visitas"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_403)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error404Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "URL não localizada"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_404)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error410Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "URL excluída permanentemente"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_410_6)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error429Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Você excedeu o limite de requisições"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_429)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error500Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "O servidor não pode atender sua solicitação neste momento"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_500_2)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error502Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Falha de comunicação entre os servidores"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_502_2)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error503Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Serviço temporariamente indisponível"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_503_2)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error504Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Esperando por muito tempo para receber a resposta do servidor"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_504)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun Error505Test() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        scenario.withFragment {
            this.mensagemError.text = "Versão HTTP não suportada"
            this.imagemError.apply {
                Glide.with(requireContext())
                    .load(R.drawable.error_505_5)
                    .centerCrop()
                    .into(this.imagemError)
            }

        }
        Espresso.onView(ViewMatchers.withId(R.id.imagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagemError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}