<script>
import * as d3 from "d3";
import { onMount } from "svelte";

export let imagenes;
// export let data;

let PATH = "/imagenes/";
let PLANET_KEY = "Tipo de SuperHeroe: "
let ANILLO_KEY = "Poder: "
let LUNA_KEY = "Cantidad de Enemigos: "
let NEBULOSA_KEY = "Tuvo una Infacia Traumatica :("
let RAYO_KEY = "Tiene una Mascota secuaz: "

let imgInfo = d3
    .scaleOrdinal()
    .domain([
        
        PATH + "planetas/BlackHole.svg",
        PATH + "planetas/NeutronStar.svg",
        PATH + "planetas/Sol.svg",
        PATH + "planetas/Tierra.svg",

        PATH + "anillos/AnilloAire.svg",
        PATH + "anillos/AnilloAgua.svg",
        PATH + "anillos/AnilloTierra.svg",
        PATH + "anillos/AnilloFuego.svg",
        PATH + "anillos/AnilloRayo.svg",

        PATH + "lunas/Luna0.svg",
        PATH + "lunas/Luna1.svg",
        PATH + "lunas/Luna2.svg",
        PATH + "lunas/Luna3.svg",
        PATH + "lunas/Luna4.svg",
        PATH + "lunas/Luna5.svg",

        
        PATH + "nebulosas/Nebulosa1",

        PATH + "rayos/RayoBlack.svg",
        PATH + "rayos/RayoNeutro.svg",
        PATH + "rayos/RayoSol.svg",
        PATH + "rayos/RayoTierra.svg",

])
    .range([
      PLANET_KEY + "Villano",
      PLANET_KEY + "Neutro",
      PLANET_KEY + "Heroe",
      PLANET_KEY + "Vengativo",

      ANILLO_KEY + "Aire",
      ANILLO_KEY + "Agua",
      ANILLO_KEY + "Tierra",
      ANILLO_KEY + "Fuego",
      ANILLO_KEY + "Rayo",

      LUNA_KEY + "0",
      LUNA_KEY + "1",
      LUNA_KEY + "2",
      LUNA_KEY + "3",
      LUNA_KEY + "4",
      LUNA_KEY + "5",

      
      NEBULOSA_KEY + "Si",

      RAYO_KEY + "Si",
      RAYO_KEY + "Si",

    ]);



    
</script>

<main>

    <div class="info-container">

        {#each imagenes as imgPath }

            <div class="img-info-container">

                <img class="mini-img" src="{imgPath}" alt="asd">

                <h5>{imgInfo(imgPath)}</h5>

            </div>
            
        {/each}


    </div>


</main>

<style>
    .info-container{
        position: relative;
        left: 100%;
        border: solid red;
        background-color: azure;
        z-index: 900;
    }

    .img-info-container{
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        padding-left: 10px;
        padding-right: 10px;

        position: relative;
    }
    .mini-img{
        max-height: 70px;
        max-width: 70px;
    }

</style>