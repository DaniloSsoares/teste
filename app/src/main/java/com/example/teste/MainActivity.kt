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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesteTheme {
                /* Surface(modifier = Modifier.fillMaxSize()) {
                     MessageCard(Message("Android", "Jetpack Compose"))*/
                Conversation(SampleData.conversationSample)
            }
        }
    }
}

data class Message(val author: String, val body: String)



@Composable
fun MessageCard(msg: Message) {
    //O Row é um componente componível do Jetpack Compose usado para organizar elementos
    // em uma linha horizontal. Ele serve como um recipiente para colocar outros componíveis,
    // como botões, textos, imagens e outros elementos visuais, e alinhá-los em uma linha horizontal.


    //adiciona preenchimento ao redor da nossa mensagem
    Row(modifier = Modifier.padding(all = 10.dp)) {

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


        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
// surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

            Text(
                text = msg.author,
                //Ele serve para aplicar uma borda a um componente componível (composable component) específico.
                color = MaterialTheme.colors.secondaryVariant,

                // definir o estilo de texto padrão em um tema específico.
                style = MaterialTheme.typography.subtitle2

            )
            // Adiciona um espaço vertical entre o autor e os textos da mensagem

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            )
            {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
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
        Message("Danilo", "Olá esse é o primeiro teste"),
        Message("Danilo", "Está tudo funcionando normalmente"),
        Message("Danilo", "Ebaaa!!!"),
        Message("Danilo", "É um estudante do ensino médio órfão que vive com seu tio Ben e sua tia May." +
                " Parker foi mordido por uma aranha radioativa em uma exposição científica e adquire a " +
                "agilidade de um aracnídeo. O estudante é capaz de andar nas paredes e tetos e desenvolve um" +
                " aparelho que o permite lançar teias artificiais."),
        Message("Danilo", "Originalmente batizado como Kakarotto (カカロット), " +
                "Goku é membro de uma raça fictícia de extraterrestres, os Saiyajins." +
                " Logo após seu nascimento, Goku é enviado à Terra por seus pais Bardock e" +
                " Gine para sobreviver à destruição do Planeta Vegeta, como revelado em Dragon Ball Super: Broly."),
        Message("Danilo", "Gohan é filho do protagonista Goku com sua esposa Chi-Chi e o" +
                " primeiro híbrido entre humano e Saiyajin mostrado na série, seu nome é uma homenagem" +
                " ao avô adotivo de seu pai, Son Gohan que foi aluno de Mestre Kame, assim como Goku." +
                " Um tema recorrente na série é o grande poder oculto de Gohan, que aos poucos é liberado."),
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