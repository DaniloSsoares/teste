package com.example.teste

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.teste.ui.theme.TesteTheme
import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}
data class Message(val author: String, val body: String)



@Composable
fun MessageCard(msg: Message){
    //O Row é um componente componível do Jetpack Compose usado para organizar elementos
    // em uma linha horizontal. Ele serve como um recipiente para colocar outros componíveis,
    // como botões, textos, imagens e outros elementos visuais, e alinhá-los em uma linha horizontal.


    //adiciona preenchimento ao redor da nossa mensagem
    Row (modifier = Modifier.padding(all = 10.dp)){

        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier

                // Define o tamanho da imagem para 40 dp
                .size(50.dp)
                // Clipe da imagem para ter a forma de um círculo
                .clip(CircleShape)
                .border(2.0.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author,
                //Ele serve para aplicar uma borda a um componente componível (composable component) específico.
            color = MaterialTheme.colors.secondaryVariant,

               // definir o estilo de texto padrão em um tema específico.
                        style = MaterialTheme.typography.subtitle2

            )
            // Adiciona um espaço vertical entre o autor e os textos da mensagem

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body,
            style = MaterialTheme.typography.body2
            )
        }
    }
}
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}
object SampleData {
    val conversationSample = listOf(
        Message("Author 1", "Message 1"),
        Message("Author 2", "Message 2"),
        Message("Author 3", "Message 3"),
        // Adicione mais mensagens de exemplo conforme necessário
    )
}

@Preview
@Composable

fun PreviewConversation() {
    TesteTheme {
        Conversation(SampleData.conversationSample)
    }
}



//tema escuro (ou modo noturno) pode ser ativado para evitar uma tela clara.
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)


@Composable
fun PreviewMessaCard(){
    TesteTheme {
        Surface {
            MessageCard(
                msg=Message("College","Hey,take a look at Jetpack Compose, it´s great!"))

        }
    }
}