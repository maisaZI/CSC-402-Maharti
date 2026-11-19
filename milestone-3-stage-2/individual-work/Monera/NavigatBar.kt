package edu.iau.cshj.csc402.navbar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.listOf

@Composable
fun BottomNavigationBar(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {

    val items = listOf(
        "Home",
        "Profile",
        "Exchanges",
        "Search",
        "Requests"
    )

    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Person,
        Icons.Filled.SwapHoriz,
        Icons.Filled.Search,
        Icons.Filled.Publish
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEachIndexed { index, item ->

            val isSelected = selectedItem == item

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onItemSelected(item)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(width = 98.dp, height = 50.dp)
                        .background(
                            color = if (isSelected)
                                Color(0xFFF1E6FA)
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        tint = if (isSelected)
                            Color(0xFF7028B8)
                        else
                            Color(0xFF51465E),
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item,
                    fontSize = 16.sp,
                    color = if (isSelected)
                        Color(0xFF7028B8)
                    else
                        Color(0xFF51465E)
                )
            }
        }
    }
}
