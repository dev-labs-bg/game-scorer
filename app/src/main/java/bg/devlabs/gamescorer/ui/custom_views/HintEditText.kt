package bg.devlabs.gamescorer.ui.custom_views

import android.content.Context
import android.content.res.TypedArray
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import bg.devlabs.gamescorer.R

/**
 * Created by Slavi Petrov on 13.09.2017
 * Dev Labs
 * slavi@devlabs.bg
 */
class HintEditText : LinearLayout {
    private lateinit var editText: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout
    private var hint: String? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    fun init(attrs: AttributeSet) {
        val attributeSet = context.obtainStyledAttributes(attrs, R.styleable.HintEditText, 0, 0)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.hint_edit_text, this, true)
        this.editText = root.findViewById(R.id.editText)
        this.textInputLayout = root.findViewById(R.id.textInputLayout)
        val imeOptions = attributeSet.getInt(R.styleable.HintEditText_ime_options, 0)
        editText.imeOptions = imeOptions
        // The text is single line
        editText.setSingleLine(true)
        this.hint = attributeSet.getString(R.styleable.HintEditText_hint)
        setupSuggestions(attributeSet)
        setupPasswordVisibility(attributeSet)
        attributeSet.recycle()
        setHint()
        setupOnTextChangedListener()
    }

    private fun setupSuggestions(attributeSet: TypedArray) {
        val suggestionsEnabled = attributeSet.getBoolean(R.styleable.HintEditText_suggestions_enabled, true)
        if (!suggestionsEnabled) {
            this.editText.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        }
    }

    private fun setupOnTextChangedListener() {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (hasError()) {
                    clearError()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not used
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not used
            }
        })
    }

    private fun setHint() {
        val hintValue = hint
        textInputLayout.hint = hintValue
    }

    fun setHint(hint: String) {
        textInputLayout.hint = hint
    }

    private fun setupPasswordVisibility(attributeSet: TypedArray) {
        val passwordVisibilityEnabled = attributeSet.getBoolean(
                R.styleable.HintEditText_password_visibility_enabled, false)
        textInputLayout.isPasswordVisibilityToggleEnabled = passwordVisibilityEnabled
        if (passwordVisibilityEnabled) {
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    val error: CharSequence?
        get() = textInputLayout.error

    fun setError(errorMessage: String) {
        textInputLayout.error = errorMessage
        textInputLayout.requestFocus()
    }

    fun setErrorEnabled(errorEnabled: Boolean) {
        textInputLayout.isErrorEnabled = errorEnabled
    }

    val text: String get() = editText.text.toString()

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        editText.setOnEditorActionListener(listener)
    }

    fun getEditText(): EditText {
        return editText
    }

    fun clearError() {
        textInputLayout.error = null
        textInputLayout.isErrorEnabled = false
    }

    fun hasError(): Boolean {
        return textInputLayout.error != null
    }
}