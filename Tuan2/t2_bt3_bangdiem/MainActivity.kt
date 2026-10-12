package com.example.t2_bt3_bangdiem

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
import com.example.t2_bt3_bangdiem.ui.theme.T2_Bt3_BangDiemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T2_Bt3_BangDiemTheme {
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
    val score: Double?
)

val danhSachSinhVien = listOf(
    Student("Đạt", 9.0),
    Student("Huy", 7.2),
    Student("Lê Minh Chi", null),
    Student("Phạm Quốc Dũng", 4.5),
    Student("Võ Thanh Em", 5.8)
)

fun xepLoai(score: Double): String {
    if (score >= 8.0) {
        return "Giỏi"
    } else if (score >= 6.5) {
        return "Khá"
    } else if (score >= 5.0) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}
fun mauXepLoai(score: Double): Color {
    if (score >= 8.0) {
        return Color(0xFF38804B)
    } else if (score >= 6.5) {
        return Color(0xFF4169A1)
    } else if (score >= 5.0) {
        return Color(0xFFB07835)
    } else {
        return Color(0xFFBB443A)
    }
}
@Composable
fun Greeting() {
    var tongDiem = 0.0
    var soNguoiCoDiem = 0
    var soGioi = 0
    var soChuaCoDiem = 0
    var diemCaoNhat = 0.0
    for (sv in danhSachSinhVien) {
        if (sv.score == null) {
            soChuaCoDiem++
        } else {
            val diem = sv.score
            tongDiem += diem
            soNguoiCoDiem++

            if (diem >= 8.0) {
                soGioi++
            }
            if (diem > diemCaoNhat) {
                diemCaoNhat = diem
            }
        }
    }
    var diemTrungBinh = 0.0
    if (soNguoiCoDiem > 0) {
        diemTrungBinh = tongDiem / soNguoiCoDiem
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
                .padding(horizontal = 16.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bảng điểm lớp",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 14.dp)
        ) {
            ThongKe(
                diemTrungBinh = diemTrungBinh,
                soGioi = soGioi,
                soChuaCoDiem = soChuaCoDiem,
                diemCaoNhat = diemCaoNhat
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "DANH SÁCH · ${danhSachSinhVien.size} SINH VIÊN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF66717D)
            )
            Spacer(modifier = Modifier.height(8.dp))
            for (sv in danhSachSinhVien) {
                TheSV(student = sv)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
@Composable
fun TheSV(student: Student) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = student.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252525)
                )
                Spacer(modifier = Modifier.height(3.dp))
                if (student.score == null) {
                    Text(
                        text = "Chưa có điểm",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                } else {
                    Text(
                        text = xepLoai(student.score),
                        fontSize = 11.sp,
                        color = mauXepLoai(student.score)
                    )
                }
            }
            if (student.score == null) {
                Text(
                    text = "—",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
            } else {
                Text(
                    text = String.format("%.1f", student.score),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252525)
                )
            }
        }
    }
}
@Composable
fun ThongKe(
    diemTrungBinh: Double,
    soGioi: Int,
    soChuaCoDiem: Int,
    diemCaoNhat: Double
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF304878),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Điểm trung bình lớp",
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Text(
            text = String.format("%.2f", diemTrungBinh),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(alpha = 0.25f))
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CotThongKe(
                tieuDe = "Giỏi",
                giaTri = "$soGioi",
                modifier = Modifier.weight(1f)
            )
            CotThongKe(
                tieuDe = "Chưa có điểm",
                giaTri = "$soChuaCoDiem",
                modifier = Modifier.weight(1f)
            )
            CotThongKe(
                tieuDe = "Cao nhất",
                giaTri = String.format("%.1f", diemCaoNhat),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
@Composable
fun CotThongKe(
    tieuDe: String,
    giaTri: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = tieuDe,
            fontSize = 11.sp,
            color = Color.LightGray
        )

        Text(
            text = giaTri,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    T2_Bt3_BangDiemTheme {
        Greeting()
    }
}
