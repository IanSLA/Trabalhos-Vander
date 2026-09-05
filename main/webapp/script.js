const form = document.getElementById("formCadastro");

const cep = document.getElementById("cep");
const logradouro = document.getElementById("logradouro");
const bairro = document.getElementById("bairro");
const cidade = document.getElementById("cidade");
const uf = document.getElementById("uf");


/* =========================
   CONSULTA DO CEP
   ========================= */

cep.addEventListener("blur", buscarCep);

async function buscarCep() {

    const valorCep = cep.value.replace(/\D/g, "");

    const erroCep = document.getElementById("erroCep");

    erroCep.textContent = "";

    if (valorCep.length !== 8) {

        erroCep.textContent =
            "Digite um CEP válido.";

        return;
    }

    try {

        const resposta = await fetch(
            `https://viacep.com.br/ws/${valorCep}/json/`
        );

        const dados = await resposta.json();

        if (dados.erro) {

            erroCep.textContent =
                "CEP não encontrado.";

            logradouro.value = "";
            bairro.value = "";
            cidade.value = "";
            uf.value = "";

            return;
        }

        logradouro.value = dados.logradouro || "";
        bairro.value = dados.bairro || "";
        cidade.value = dados.localidade || "";
        uf.value = dados.uf || "";

    } catch (erro) {

        erroCep.textContent =
            "Não foi possível consultar o CEP.";
    }
}


/* =========================
   VALIDAÇÃO DO FORMULÁRIO
   ========================= */

form.addEventListener("submit", function(event) {

    let valido = true;

    const nome = document.getElementById("nome");
    const email = document.getElementById("email");
    const telefone = document.getElementById("telefone");

    const erroNome = document.getElementById("erroNome");
    const erroEmail = document.getElementById("erroEmail");
    const erroTelefone = document.getElementById("erroTelefone");
    const erroCep = document.getElementById("erroCep");
    const mensagem = document.getElementById("mensagem");


    /* Limpa mensagens anteriores */

    erroNome.textContent = "";
    erroEmail.textContent = "";
    erroTelefone.textContent = "";
    erroCep.textContent = "";
    mensagem.textContent = "";


    /* Validação do nome */

    if (nome.value.trim() === "") {

        erroNome.textContent =
            "Informe o nome.";

        valido = false;
    }


    /* Validação do e-mail */

    if (email.value.trim() === "") {

        erroEmail.textContent =
            "Informe o e-mail.";

        valido = false;

    } else if (!email.validity.valid) {

        erroEmail.textContent =
            "Informe um e-mail válido.";

        valido = false;
    }


    /* Validação do telefone */

    if (telefone.value.trim() === "") {

        erroTelefone.textContent =
            "Informe o telefone.";

        valido = false;
    }


    /* Validação do CEP */

    const valorCep = cep.value.replace(/\D/g, "");

    if (valorCep.length !== 8) {

        erroCep.textContent =
            "Informe um CEP válido.";

        valido = false;
    }


    /* Impede o envio se houver erro */

    if (!valido) {

        event.preventDefault();

        mensagem.textContent =
            "Corrija os campos indicados.";

        return;
    }


    /*
       Se estiver tudo correto,
       o formulário será enviado
       para o ClienteServlet.
    */

});