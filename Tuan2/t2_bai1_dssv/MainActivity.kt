package com.example.t2_bai1_dssv

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
import com.example.t2_bai1_dssv.ui.theme.T2_Bai1_DssvTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T2_Bai1_DssvTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){
                    Greeting()
                }
            }
        }
    }
}
data class Student(
    val name: String,
    val email: String?,
    val phone: String?)
val danhSachSinhVien = listOf(
    Student( "Đạt", "an@sv.edu.vn", "0901 234 567"),
    Student( "Trần Thị Bình", null, "0912 888 999"),
    Student("Lê Minh Chi", "chi@sv.edu.vn", null),
    Student("Huy", null, null),
    Student("Võ Thanh Em", "em@sv.edu.vn", "0933 111 222")
)

@Composable
fun Greeting() {
    var demThieuEmail = 0
    for (sv in danhSachSinhVien) {
        if (sv.email == null) {
            demThieuEmail++
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF304878))
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Danh sách sinh viên",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            for (sv in danhSachSinhVien) {
                TheSV(student = sv)
                Spacer(modifier = Modifier.height(10.dp))
            }

            TongSinhVien(
                tongSo = danhSachSinhVien.size,
                thieuEmail = demThieuEmail
            )
        }
    }
}

@Composable
fun TheSV(student: Student) {
    val emailHienThi = student.email ?: "Chưa cập nhật"
    val phoneHienThi = student.phone ?: "Chưa cập nhật"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Text(
            text = student.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF252525)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row {
            Text(
                text = "Email: ",
                fontSize = 12.sp,
                color = Color(0xFF666666)
            )

            Text(
                text = emailHienThi,
                fontSize = 12.sp,
                color = if (student.email == null) {
                    Color(0xFFB65C4A)
                } else {
                    Color(0xFF666666)
                }
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row {
            Text(
                text = "SĐT: ",
                fontSize = 12.sp,
                color = Color(0xFF666666)
            )
            Text(
                text = phoneHienThi,
                fontSize = 12.sp,
                color = if (student.phone == null) {
                    Color(0xFFB65C4A)
                } else {
                    Color(0xFF666666)
                }
            )
        }
    }
}

@Composable
fun TongSinhVien(
    tongSo: Int,
    thieuEmail: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE2EAF5),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = "Tổng: ",
                fontSize = 12.sp,
                color = Color(0xFF304878)
            )

            Text(
                text = "$tongSo sinh viên",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF304878)
            )
        }
        Row {
            Text(
                text = "Thiếu email: ",
                fontSize = 12.sp,
                color = Color(0xFF304878)
            )

            Text(
                text = "$thieuEmail",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCB5148)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    T2_Bai1_DssvTheme {
        Greeting()
    }
}