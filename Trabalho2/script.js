const formulario = document.getElementById("formCadastro");

const nome = document.getElementById("nome");
const email = document.getElementById("email");
const telefone = document.getElementById("telefone");
const cep = document.getElementById("cep");

const logradouro = document.getElementById("logradouro");
const bairro = document.getElementById("bairro");
const cidade = document.getElementById("cidade");
const uf = document.getElementById("uf");

const mensagem = document.getElementById("mensagem");

const listaClientes = document.getElementById("listaClientes");


// ==============================
// CONSULTA DO CEP
// ==============================

cep.addEventListener("blur", buscarCep);

async function buscarCep() {

    const valorCep = cep.value.replace(/\D/g, "");

    // Se o CEP não tiver 8 números, não faz a consulta
    if (valorCep.length !== 8) {
        limparEndereco();
        return;
    }

    try {

        logradouro.value = "Consultando...";
        bairro.value = "Consultando...";
        cidade.value = "Consultando...";
        uf.value = "Consultando...";

        const resposta = await fetch(
            `https://viacep.com.br/ws/${valorCep}/json/`
        );

        if (!resposta.ok) {
            throw new Error("Erro na consulta do CEP.");
        }

        const dados = await resposta.json();

        // CEP não encontrado
        if (dados.erro) {

            limparEndereco();

            document.getElementById("erroCep").textContent =
                "CEP não encontrado.";

            return;
        }

        // Preenche os campos automaticamente
        logradouro.value = dados.logradouro;
        bairro.value = dados.bairro;
        cidade.value = dados.localidade;
        uf.value = dados.uf;

        document.getElementById("erroCep").textContent = "";

    } catch (erro) {

        limparEndereco();

        document.getElementById("erroCep").textContent =
            "Não foi possível consultar o CEP.";

        console.error(erro);
    }
}


// ==============================
// LIMPAR ENDEREÇO
// ==============================

function limparEndereco() {

    logradouro.value = "";
    bairro.value = "";
    cidade.value = "";
    uf.value = "";
}


// ==============================
// VALIDAÇÃO DO FORMULÁRIO
// ==============================

formulario.addEventListener("submit", function(event) {

    // Impede o navegador de recarregar a página
    event.preventDefault();

    let formularioValido = true;

    // Limpa mensagens anteriores
    document.querySelectorAll(".erro").forEach(function(elemento) {
        elemento.textContent = "";
    });

    mensagem.textContent = "";
    mensagem.className = "mensagem";


    // ------------------------------
    // Nome
    // ------------------------------

    if (nome.value.trim() === "") {

        document.getElementById("erroNome").textContent =
            "Informe o nome.";

        formularioValido = false;
    }


    // ------------------------------
    // E-mail
    // ------------------------------

    const emailValido =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailValido.test(email.value.trim())) {

        document.getElementById("erroEmail").textContent =
            "Informe um e-mail válido.";

        formularioValido = false;
    }


    // ------------------------------
    // Telefone
    // ------------------------------

    const telefoneNumeros =
        telefone.value.replace(/\D/g, "");

    if (telefoneNumeros.length < 10) {

        document.getElementById("erroTelefone").textContent =
            "Informe um telefone válido.";

        formularioValido = false;
    }


    // ------------------------------
    // CEP
    // ------------------------------

    const cepNumeros =
        cep.value.replace(/\D/g, "");

    if (cepNumeros.length !== 8) {

        document.getElementById("erroCep").textContent =
            "Informe um CEP válido.";

        formularioValido = false;
    }


    // ==============================
    // SE ESTIVER TUDO CORRETO
    // ==============================

    if (formularioValido) {

        // Cria uma nova linha para a tabela
        const novaLinha = document.createElement("tr");

        // Insere os dados do formulário
        novaLinha.innerHTML = `
            <td>${nome.value.trim()}</td>
            <td>${email.value.trim()}</td>
            <td>${telefone.value.trim()}</td>
            <td>${cidade.value.trim()}</td>
        `;

        // Adiciona a nova linha na tabela
        listaClientes.appendChild(novaLinha);

        // Mostra mensagem de sucesso
        mensagem.textContent =
            "Cadastro realizado com sucesso!";

        mensagem.className = "mensagem sucesso";

        // Limpa o formulário
        formulario.reset();

        // Limpa os campos de endereço
        limparEndereco();
    }

});