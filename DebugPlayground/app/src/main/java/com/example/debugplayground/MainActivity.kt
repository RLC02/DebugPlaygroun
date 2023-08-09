package com.example.debugplayground

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.debugplayground.ui.theme.DebugButtonColors
import com.example.debugplayground.ui.theme.DebugPlaygroundTheme
import com.example.debugplayground.ui.theme.ErrorButtonColors
import com.example.debugplayground.ui.theme.InfoButtonColors
import com.example.debugplayground.ui.theme.WarningButtonColors
import java.lang.Exception


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebugPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }
}

@Composable
private fun App(){ //mover a função da classe
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column (
            //Alinhamento vertical e horizontal
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Greeting("Playground") //Alterando a Preview
            //Adicionar ação para o botão
            ActionButton(
                text = "Debug",
                buttonColors = DebugButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.d(TAG, "Clicou em Debug")
            }
            ActionButton(
                text = "Info",
                buttonColors = InfoButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.d(TAG, "Clicou em Info!")
            }
            ActionButton(
                text = "Warning",
                buttonColors = WarningButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.d(TAG, "Clicou em Warning!",)
            }
            ActionButton(
                text = "Error",
                buttonColors = ErrorButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){ //tratar ação para evitar de derrubar App
                try{
                    throw java.lang.RuntimeException("Clicou em ERROR")
                } catch (ex : Exception){
                    Log.e(TAG, "${ex.message}",)
                }
            }
        }
    }
}

//cria a função ActionButton para trabalhar as ações dos botões com três parametros diferentes
@Composable
fun ActionButton(
    text:String,
    buttonColors:ButtonColors = ButtonDefaults.buttonColors(), //encapsulando as cores dos botoes
    modifier: Modifier = Modifier, //modificando o botao estado vazio
    block: () -> Unit, //passando bloco como parametro para instanciar o botao
){
    //informar o que tem o composable
    ElevatedButton(
        onClick=block,
        shape = RoundedCornerShape(5.dp), //botoes com bordas arredondadas
        colors = buttonColors,
        modifier = modifier //modificando o botao
    ){
        Text(text = text)
    }
}

//criando Preview do ActionButton
@Preview( showBackground =  true , widthDp = 120)
@Composable
fun ActionButtonPreview(){
    ActionButton(text = "Action") {

    }
}

//Criar a função preview
@Preview (showBackground = true, widthDp = 320, heightDp = 480) //alterando o layout do App
@Composable
fun AppPreview(){
    DebugPlaygroundTheme {
        App() //chamar a funcao App
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    Text(
        text = "Welcome to $name!", //Concatenação de String
        //Customizar o Tema
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    DebugPlaygroundTheme {
        Greeting("Android") //Alterando a Preview da função para que a mesma possa funcionar
    }
}