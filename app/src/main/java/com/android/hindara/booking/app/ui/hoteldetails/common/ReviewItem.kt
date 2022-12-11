package com.android.hindara.booking.app.ui.hoteldetails.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.hindara.booking.app.R
import com.core.model.hotel_details.Reviews

@Composable
fun ReviewItemComposable(
    review: Reviews
) {
    ConstraintLayout {
        val (reviewerImage, reviewerName, rating, reviewComment) = createRefs()

        val defaultSpacing = dimensionResource(id = R.dimen.default_spacing)
        val smallSpacing = dimensionResource(id = R.dimen.small_spacing)
        val reviewerImageModifier = Modifier
            .clip(CircleShape)
            .size(
                width = dimensionResource(id = R.dimen.profile_image_width),
                height = dimensionResource(id = R.dimen.profile_image_height)
            )
            .constrainAs(reviewerImage) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        Image(
            modifier = reviewerImageModifier,
            contentScale = ContentScale.Crop,
            painter = painterResource(id = review.reviewImage),
            contentDescription = stringResource(R.string.image_review_person)
        )

        val nameModifier = Modifier.constrainAs(reviewerName) {
            start.linkTo(reviewerImage.end, margin = defaultSpacing)
            top.linkTo(reviewerImage.top)
        }
        Text(
            modifier = nameModifier,
            text = review.reviewerName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h2
        )

        val ratingModifier = Modifier.constrainAs(rating) {
            start.linkTo(reviewerImage.end, margin = defaultSpacing)
            top.linkTo(reviewerName.bottom)
        }
        LazyRow(
            modifier = ratingModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(5) {
                Image(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = R.dimen.rating_star_size),
                            height = dimensionResource(id = R.dimen.rating_star_size)
                        )
                        .padding(end = dimensionResource(id = R.dimen.tiny_spacing)),
                    painter = painterResource(id = R.drawable.ic_star), contentDescription = null
                )
            }
        }

        val reviewCommentModifier = Modifier
            .constrainAs(reviewComment) {
                start.linkTo(parent.start, margin = defaultSpacing)
                top.linkTo(rating.bottom, margin = smallSpacing)
                end.linkTo(parent.end, margin = defaultSpacing)
                bottom.linkTo(parent.bottom, margin = smallSpacing)
            }
            .fillMaxWidth()

        Text(
            modifier = reviewCommentModifier,
            text = review.comment,
            style = MaterialTheme.typography.body1
        )
    }
}