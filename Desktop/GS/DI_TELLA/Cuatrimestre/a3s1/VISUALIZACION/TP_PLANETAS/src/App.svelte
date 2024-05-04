<script>
  import * as d3 from "d3";
  import { onMount } from "svelte";
  import Planeta from './Planeta.svelte';
  import Draggable from './Draggable.svelte';

  let scale = 1;
  let isDragging = false;
  let startX, startY;
  let container; // Referencia al contenedor, se establece después de montar el componente


  let PATH = "./imagenes";
  /* Array donde guardaremos la data */
  let planetas = [];



	function zoom(event) {
    scale += event.deltaY * -0.001
	  // Restricción de escala
	  scale = Math.min(Math.max(1, scale), 12);
	}




  onMount(() => {
    
    d3.csv("./data/encuesta_pro.csv", d3.autoType).then((data) => {
      console.log(data);
      
      

      data.forEach((d) => {
        d.angulo = Math.random() * 360;
        d.radio = 10 * Math.log10(d.cant_personas);
      });
      

      planetas = data;
      console.log("PLANETA: ");
      console.log(planetas);

    });
  });
</script>





  

    
    
    <h3 id="titulo">Galaxia <br>Tecnologia Digital <br>2024 seccion Araujo</h3>

    <!-- svelte-ignore a11y-no-static-element-interactions -->
    
    <Draggable>
      <div class="planetContainer" 
        on:wheel|preventDefault={(e) => zoom(e)}
        style="transform: scale({scale})">

        

        {#each planetas as row,index}
    
              <div class="planeta" >

                <Planeta data={planetas[index]}/>

              </div>

        {/each}

      </div>
    </Draggable>
    





<style>



  .planeta{
    /* position: relative; */
    transform: scale(0.6);
    width: auto;
    height: auto;
    /* display: inline-block; */
    /* border: solid red; */
    min-width: 150px;
    min-height: 150px;
  }



  .planetContainer{
    position: relative;
    border: solid rgb(0, 21, 255,0.5);
    /* width: 2 ; */
    display: flex;
    top:40%;
    justify-content: space-between;
    flex-wrap: wrap;
    /* grid-template-columns: repeat(6, 1fr);
    grid-template-rows: auto auto; */
    width: 98%;
    height: 98%;
    
  }

  #titulo{
    position: fixed;
    width: 100%;
    font-size: larger;
    text-align: center;
    color: #ffffff;
    z-index: 10;
  }
</style>
