
import com.example.composeapiconsume.android.core.app.Constants
import com.example.composeapiconsume.android.core.resources.theme.AppTheme
import io.paperdb.Paper
import java.util.*

object AppPreferences {

    //region LanguageConfig
    //key
    private const val APP_LANG = "AppLang"

    fun setLocale(lang: String) {
        Paper.book().write(APP_LANG, lang)
    }

    fun getLocale(): String {
        return when(Paper.book().read(APP_LANG, Constants.DEFAULT_SYSTEM_LOCALE_LANG)!!) {
            Constants.DEFAULT_SYSTEM_LOCALE_LANG -> {
                Locale.getDefault().language
            }
            Constants.ARABIC_LOCALE_LANG -> {
                Constants.ARABIC_LOCALE_LANG
            }
            Constants.ENGLISH_LOCALE_LANG -> {
                Constants.ENGLISH_LOCALE_LANG
            }
            else -> {
                Constants.ENGLISH_LOCALE_LANG
            }
        }
    }

    fun getSelectedLanguage(): String {
        return Paper.book().read(APP_LANG, Constants.DEFAULT_SYSTEM_LOCALE_LANG)!!
    }

    //endregion

    //region Theme

    private const val APP_THEME = "AppTheme"

    fun setTheme(theme: AppTheme) {
        Paper.book().write(APP_THEME, theme)
    }

    fun getTheme(): AppTheme {
        return Paper.book().read(APP_THEME, AppTheme.Default)!!
    }

    //endregion
}