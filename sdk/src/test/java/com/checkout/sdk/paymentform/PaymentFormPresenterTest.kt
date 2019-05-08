package com.checkout.sdk.paymentform

import com.checkout.sdk.architecture.MvpView
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PaymentFormPresenterTest {

    @Mock
    private lateinit var viewMock: MvpView<PaymentFormUiState>

    @Mock
    private lateinit var getTokenUseCase: GetTokenUseCase

    private lateinit var presenter: PaymentFormPresenter

    private lateinit var initialState: PaymentFormUiState

    @BeforeEach
    fun onSetup() {
        initialState = PaymentFormUiState()
        presenter = PaymentFormPresenter()
        presenter.start(viewMock)
        Mockito.reset(viewMock)
    }

    @Test
    fun `when we get token we execute the use case`() {
        presenter.getToken(getTokenUseCase)

        then(getTokenUseCase).should().execute()
    }

    @Test
    fun `when we change progress we change state to show the new progress`() {
        val expectedProgress = true
        presenter.onProgressChanged(true)

        then(viewMock).should().onStateUpdated(initialState.copy(inProgress = expectedProgress))
    }

    @Test
    fun `when we change screen we update view with new screen to show`() {
        val expectedScreenShowing = PaymentFormUiState.Showing.BILLING_DETAILS_SCREEN
        presenter.changeScreen(expectedScreenShowing)

        then(viewMock).should().onStateUpdated(uiState = initialState.copy(showing = expectedScreenShowing))
    }

}
