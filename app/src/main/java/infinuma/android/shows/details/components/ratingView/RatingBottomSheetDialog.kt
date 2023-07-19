package infinuma.android.shows.details.components.ratingView

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import infinuma.android.shows.databinding.DialogAddReviewBinding

class RatingBottomSheetDialog(
    private val onClick: (grade: Int, review: String) -> Unit,
    context: Context,
) : BottomSheetDialog(context) {

    private lateinit var binding: DialogAddReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gradeRatingBar.setOnRatingBarChangeListener { _, _, _ ->
            binding.addReviewButton.isEnabled = true
        }
        binding.addReviewButton.setOnClickListener {
            onClick(
                binding.gradeRatingBar.rating.toInt(),
                binding.reviewEditText.text.toString(),
            )
            dismiss()
        }
    }
}