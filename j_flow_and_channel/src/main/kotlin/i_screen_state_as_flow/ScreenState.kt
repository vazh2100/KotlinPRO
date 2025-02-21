package i_screen_state_as_flow

sealed class ScreenState {
    data object Initial : ScreenState()
    data object Loading : ScreenState()
    data object NotFound : ScreenState()
    data class Loaded(val content: String) : ScreenState()
}