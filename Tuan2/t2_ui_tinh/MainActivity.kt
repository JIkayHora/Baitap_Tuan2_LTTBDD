package com.example.t2_ui_tinh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.t2_ui_tinh.ui.theme.T2_UI_TinhTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T2_UI_TinhTheme {
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
    var so1 by remember { mutableStateOf( value = "") }
    var so2 by remember { mutableStateOf( value = "") }
    var phepTinh by remember { mutableStateOf( value = "") }
    var ketQua by remember { mutableStateOf( value = "") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        TieuDe()
        Spacer(modifier = Modifier.height(60.dp))
        Nhap(
            giaTri = so1,
            userNhap = { so1 = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        NutTinh(
            phepTinh = phepTinh,
            userChon = {
                phepTinh = it
                ketQua = Tinh(so1, so2, phepTinh)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Nhap(
            giaTri = so2,
            userNhap = { so2 = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (ketQua.isEmpty()) {
                "Kết quả:"
            } else {
                "Kết quả: $ketQua"
            },
            modifier = Modifier.fillMaxWidth(),
            fontSize = 14.sp
        )
    }

}
@Composable
fun Nhap( giaTri: String, userNhap: (String) -> Unit
) {
    OutlinedTextField(
        value = giaTri,
        onValueChange = { userNhap(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        singleLine = true,
        shape = RoundedCornerShape( size = 16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}
@Composable
fun TieuDe()
{
    Text(
        text = "Thực hành 03",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )
}
@Composable
fun NutTinh( phepTinh: String, userChon: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NutPhepTinh(
            kyHieu = "+",
            dangChon = phepTinh == "+",
            mau = Color(0xFFD94F45),
            khiBam = { userChon("+") }
        )

        NutPhepTinh(
            kyHieu = "-",
            dangChon = phepTinh == "-",
            mau = Color(0xFFE0B34F),
            khiBam = { userChon("-") }
        )

        NutPhepTinh(
            kyHieu = "*",
            dangChon = phepTinh == "*",
            mau = Color(0xFF5938D9),
            khiBam = { userChon("*") }
        )

        NutPhepTinh(
            kyHieu = "/",
            dangChon = phepTinh == "/",
            mau = Color(0xFF202020),
            khiBam = { userChon("/") }
        )
    }
}
@Composable
fun NutPhepTinh(kyHieu: String, dangChon: Boolean, mau: Color ,khiBam: () -> Unit)
{
    Button(
        onClick = khiBam,
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape( size = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = mau
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (dangChon) 4.dp else 0.dp
        )
    ) {
        Text(
            text = kyHieu,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun Dau(kyHieu: String, dangChon: Boolean, khiBam: () -> Unit)

{
    Button(
        onClick = khiBam,
        modifier = Modifier.size(48.dp),
        shape = RoundedCornerShape( size = 8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues (0.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (dangChon) 4.dp else 0.dp
        )
    ) {
        Text(
            text = kyHieu,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

fun Tinh(so1: String, so2: String, phepTinh: String): String
{
    val a = so1.toDoubleOrNull()
    val b = so2.toDoubleOrNull()

    if (a == null || b == null || phepTinh.isEmpty()) {
        return ""
    }

    val ketQua = when (phepTinh) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> {
            if (b == 0.0) {
                return "Không thể chia cho 0"
            }
            a / b
        }
        else -> return ""
    }
    return if (ketQua % 1.0 == 0.0) {
        ketQua.toLong().toString()
    } else {
        ketQua.toString()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    T2_UI_TinhTheme {
        Greeting()
    }
}