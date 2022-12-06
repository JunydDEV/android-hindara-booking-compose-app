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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.android.hindara.booking.app.R
import com.android.hindara.booking.app.ui.home.Reviews
import com.android.hindara.booking.app.ui.theme.DarkTextColor

@Composable
fun ReviewItemComposable(
    review: Reviews
) {
    ConstraintLayout {
        val (reviewerImage, reviewerName, rating, reviewComment) = createRefs()

        val spacing = dimensionResource(id = R.dimen.default_spacing)
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
            painter = painterResource(id = review.reviewImage),
            contentDescription = stringResource(R.string.image_review_person)
        )

        val nameModifier = Modifier.constrainAs(reviewerName) {
            start.linkTo(reviewerImage.end, margin = spacing)
            top.linkTo(reviewerImage.top)
        }
        Text(
            modifier = nameModifier,
            text = review.reviewerName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h2,
            color = DarkTextColor
        )

        val ratingModifier = Modifier.constrainAs(rating) {
            start.linkTo(reviewerImage.end, margin = spacing)
            top.linkTo(reviewerName.bottom)
        }
        LazyRow(
            modifier = ratingModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(5) {
                Image(
                    modifier = Modifier
                        .size(18.dp, 18.dp)
                        .padding(end = dimensionResource(id = R.dimen.tiny_spacing)),
                    painter = painterResource(id = R.drawable.ic_star), contentDescription = null
                )
            }
        }

        val reviewCommentModifier = Modifier
            .constrainAs(reviewComment) {
                start.linkTo(parent.start, margin = spacing)
                top.linkTo(rating.bottom, margin = spacing)
                end.linkTo(parent.end, margin = spacing)
                bottom.linkTo(parent.bottom, margin = spacing)
            }
            .fillMaxWidth()

        Text(
            modifier = reviewCommentModifier,
            text = review.comment,
            style = MaterialTheme.typography.body1,
            color = DarkTextColor
        )
    }
}