async function convertir(opcion) {

    const cantidad = parseFloat(document.getElementById("cantidad").value);

    if (!cantidad) {
        alert("Enter an amount");
        return;
    }

    let base = "";
    let destino = "";

    // Mapear opciones
    switch(opcion) {
        case 1:
            base = "USD";
            destino = "ARS";
            break;
        case 2:
            base = "ARS";
            destino = "USD";
            break;
        case 3:
            base = "USD";
            destino = "BRL";
            break;
        case 4:
            base = "BRL";
            destino = "USD";
            break;
        case 5:
            base = "USD";
            destino = "COP";
            break;
        case 6:
            base = "COP";
            destino = "USD";
            break;
        case 7:
            base = "USD";
            destino = "MXN";
            break;
        case 8:
            base = "MXN";
            destino = "USD";
            break;
        default:
            alert("Invalid option");
            return;
    }

    try {
        const response = await fetch(
            `/api/convertir?base=${base}&destino=${destino}`
        );

        const data = await response.json();

        // Mostrar resultado
        const resultadoFinal = cantidad * data.resultado;

        document.getElementById("resultado").innerText =
            `${cantidad} ${base} = ${resultadoFinal.toFixed(2)} ${destino}`;

    } catch (error) {
        console.error(error);
        document.getElementById("resultado").innerText =
            "Error connecting to the server";
    }
}
//function convertir(opcion) {
//      const cantidad = parseFloat(document.getElementById("cantidad").value);
//      let resultado = 0;
//
//      if (isNaN(cantidad)) {
//        document.getElementById("resultado").innerText = "Ingrese una cantidad válida";
//        return;
//      }
//
//      switch(opcion) {
//        case 1: resultado = cantidad * 850; break;   // ejemplo tasas
//        case 2: resultado = cantidad / 850; break;
//        case 3: resultado = cantidad * 5.0; break;
//        case 4: resultado = cantidad / 5.0; break;
//        case 5: resultado = cantidad * 4000; break;
//        case 6: resultado = cantidad / 4000; break;
//      }
//
//      document.getElementById("resultado").innerText = "Amount: " + resultado.toFixed(2);
//    }
//
//    function salir() {
//      document.getElementById("resultado").innerText = "Aplicación finalizada";
//    }