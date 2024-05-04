<script>
  import * as d3 from "d3";
  import { onMount } from "svelte";
  import Info from "./Info.svelte";

  export let data;
  let isLoaded = false
  let PATH = "/imagenes/";
  let showName = false

  /* 2. Escala para Tipo de planeta */
  let planeta = d3
    .scaleOrdinal()
    .domain(["Heroe", "Villano", "Neutro", "Vengativo"])
    .range([
      PATH + "planetas/Sol.svg",
      PATH + "planetas/BlackHole.svg",
      PATH + "planetas/NeutronStar.svg",
      PATH + "planetas/Tierra.svg",
    ]);

  /* 2. Escala para Tipo de planeta */
  let rayo = d3
    .scaleOrdinal()
    .domain(["Heroe", "Villano", "Neutro", "Vengativo"])
    .range([
      PATH + "rayos/RayoSol.svg",
      PATH + "rayos/RayoBlack.svg",
      PATH + "rayos/RayoNeutro.svg",
      PATH + "rayos/RayoTierra.svg",
    ]);

    let lunas = d3
    .scaleOrdinal()
    .domain([0, 1, 2, 3, 4, 5])
    .range([
      PATH + "lunas/Luna0.svg",
      PATH + "lunas/Luna1.svg",
      PATH + "lunas/Luna2.svg",
      PATH + "lunas/Luna3.svg",
      PATH + "lunas/Luna4.svg",
      PATH + "lunas/Luna5.svg",
    ]);

    let estrellas = d3
      .scaleThreshold()
      .domain([0,10,50,200,1000,10000,100000])
      .range([
      PATH + "estrellas/e1-10.svg",
      PATH + "estrellas/e10-50.svg",
      PATH + "estrellas/e50-200.svg",
      PATH + "estrellas/e200-1000.svg",
      PATH + "estrellas/e1000-10000.svg",
      PATH + "estrellas/e10000-100000.svg",
    ]);

  let svgs = [
    "/imagenes/lunas/Luna0.svg"
    // "/imagenes/planetas/BlackHole.svg",
    // "/imagenes/anillos/AnilloRayo.svg",
    // "/imagenes/anillos/AnilloAgua.svg",
    // "/imagenes/anillos/AnilloAire.svg",
    // "/imagenes/rayos/RayoBlack.svg",
  ];
  // Agrega más paths según el número de imágenes SVG

  console.log(svgs);

  let containerSize = 0; // Se calculará el tamaño del contenedor
  let dimensions = {};
  let scales = {};

  onMount(() => {
    
    addNebulosa(svgs, data);
    // addEstrellas(svgs,data);
    addPlanet(svgs, data);
    addRayo(svgs, data);
    addPowers(svgs, data);
    addLunas(svgs,data);

    if (svgs.length > 0) {
      const images = document.querySelectorAll(".overlay-svg");
      images.forEach((img) => {
        img.onload = () => {
          dimensions[img.src] = {
            width: img.naturalWidth,
            height: img.naturalHeight,
          };
          containerSize = Math.max(
            containerSize,
            img.naturalWidth,
            img.naturalHeight
          );
          calculateScales();
        };
      });
      isLoaded = true
    } else {
      // Handle the case where no images are available
      console.log("No images to display");
      // Optionally, set a minimum size for the container or show a placeholder
      containerSize = 200; // Default size or set based on your UI needs
    }
    console.log("LISTA: ", svgs);
  });

  function calculateScales() {
    let maxScale = 0;
    // Calcula la escala necesaria para que la imagen más grande encaje en el contenedor
    for (let key in dimensions) {
      const scaleX = containerSize / dimensions[key].width;
      const scaleY = containerSize / dimensions[key].height;
      const requiredScale = Math.min(scaleX, scaleY);
      scales[key] = requiredScale;
      maxScale = Math.max(maxScale, requiredScale);
    }
    // Ajusta todas las escalas relativas a la imagen más grande
    for (let key in scales) {
      scales[key] /= maxScale;
    }
  }

  function addPowers(imagenes, dato) {
    if (dato.aire == 1) {
      imagenes.push("/imagenes/anillos/AnilloAire.svg");
    }
    if (dato.agua == 1) {
      imagenes.push("/imagenes/anillos/AnilloAgua.svg");
    }
    if (dato.tierra == 1) {
      imagenes.push("/imagenes/anillos/AnilloTierra.svg");
    }
    if (dato.fuego == 1) {
      imagenes.push("/imagenes/anillos/AnilloFuego.svg");
    }
    if (dato.rayo == 1) {
      imagenes.push("/imagenes/anillos/AnilloRayo.svg");
    }
  }

  function addPlanet(imagenes, dato) {
    imagenes.push(planeta(dato.tipo));
  }

  function addNebulosa(imagenes, dato) {
    if (dato.trauma == 1) {
      imagenes.push("/imagenes/nebulosas/Nebulosa.svg");
    }
  }

  function addRayo(imagenes, dato) {
    if (dato.mascota == 1) {
      imagenes.push(rayo(dato.tipo));
    }
  }

  function addLunas(imagenes, dato){
    imagenes.push(lunas(dato.enemigos))
  }
  function addEstrellas(imagenes,dato){
    image = estrellas(dato.cant_personas);
    if(image != ""){
      imagenes.push(image);
    }

  }
</script>


<!-- svelte-ignore a11y-no-static-element-interactions -->

<div class="planet-container"
    on:mouseenter={() => showName = true}
    on:mouseleave={() => showName = false}>
  
  <div
    class="svg-container"
    style={`width: ${containerSize}px; height: ${containerSize}px;`}
  >
    {#if isLoaded}
      {#each svgs as svg,i}
        
        <img
          src={svg}
          alt="SVG"
          class="overlay-svg"
          style={`transform: translate(-50%, -50%) scale(${scales[svg] || 1});`}
        />
      {/each}
    {/if}
  </div>

</div>

{#if showName}
    <h3 class="planet-name">{data.Nombre}</h3>
    <Info imagenes={svgs} />
{/if}


<style>
  
  h1, h3{
    position: fixed;
    margin-left: 5%;
    text-align: center;
    color: rgb(255, 255, 255);
    /* border: solid yellow; */
    /* z-index: 11; */
  }

  .planet-name{
    top: 40%;
  }
  .planet-container{
    display: flex;
    justify-content: center;
    z-index: 20;
  }
  .svg-container {
    position: relative;
    /* border: solid red; */
    display: flex;
    align-items: center;
    justify-content: center;
    /*overflow: hidden; /* Previene que las imágenes escaladas se desborden */
    transform: scale(0.3);
  }
  .overlay-svg {
    position: absolute;
    top: 50%;
    left: 50%;
    transform-origin: center center; /* Asegura que la transformación de escala y translación sea desde el centro */
  }

</style>
