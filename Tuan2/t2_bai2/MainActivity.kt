package com.example.t2_bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.t2_bai2.ui.theme.T2_bai2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T2_bai2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val diem = 8.5
    val loai = xepLoai(diem)
    val danhSachDiem = listOf(9.0, 7.0, 5.5, 3.0, 11.0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F2F7))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF304878))
                .padding(horizontal = 20.dp, vertical = 50.dp)
        ) {
            Text(
                text = "Xếp loại học lực",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Điểm trung bình",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$diem",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF20242D)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = loai,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0xFFE5F2E8),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 10.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = mauXepLoai(loai),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "KIỂM TRA THÊM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF69717E)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            ) {
                for (i in danhSachDiem.indices) {
                    DiemItem(diem = danhSachDiem[i])

                    if (i < danhSachDiem.lastIndex) {
                        Spacer(modifier = Modifier.height(1.dp))
                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color(0xFFEEEEEE))
                        )
                    }
                }
            }
        }
    }
}

fun xepLoai(diem: Double): String {
    if (diem < 0 || diem > 10) {
        return "Không hợp lệ"
    } else if (diem >= 8.0) {
        return "Giỏi"
    } else if (diem >= 6.5) {
        return "Khá"
    } else if (diem >= 5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}
fun mauXepLoai(loai: String): Color {
    if (loai == "Giỏi") {
        return Color(0xFF39764B)
    } else if (loai == "Khá") {
        return Color(0xFF315FA8)
    } else if (loai == "Trung bình") {
        return Color(0xFFAC741D)
    } else if (loai == "Yếu") {
        return Color(0xFFB74D43)
    } else {
        return Color(0xFF777D89)
    }
}
@Composable
fun DiemItem(diem: Double) {
    val loai = xepLoai(diem)
    val nen = if (loai == "Giỏi") {
        Color(0xFFE5F2E8)
    } else if (loai == "Khá") {
        Color(0xFFE5EDF9)
    } else if (loai == "Trung bình") {
        Color(0xFFFFF1DA)
    } else if (loai == "Yếu") {
        Color(0xFFF9E8E5)
    } else {
        Color(0xFFEDEEF2)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Điểm ${diem.toString().removeSuffix(".0")}",
            fontSize = 13.sp,
            color = Color(0xFF252525)
        )
        Text(
            text = loai,
            modifier = Modifier
                .background( color = nen, shape = RoundedCornerShape(size = 5.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = mauXepLoai(loai)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    T2_bai2Theme {
        Greeting()
    }
}