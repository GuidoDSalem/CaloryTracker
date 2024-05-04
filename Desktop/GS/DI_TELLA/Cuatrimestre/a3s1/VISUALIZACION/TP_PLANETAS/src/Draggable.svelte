<script>
	export let left = 0;
	export let top = 0;
	
	let moving = false;
	
	function onMouseDown() {
		moving = true;
	}
	
	function onMouseMove(e) {
		if (moving) {
			left += e.movementX;
			top += e.movementY;
		}
	}
	
	function onMouseUp() {
		moving = false;
	}
	
// 	$: console.log(moving);
</script>

<style>
	.draggable {
		user-select: none;
		/* cursor: move; */
		/* border: solid 1px gray; */
		position: absolute;
        width: 100%;
        height: 100%;
	}
</style>

<!-- svelte-ignore a11y-no-static-element-interactions -->
<section on:mousedown={onMouseDown} style="left: {left}px; top: {top}px;" class="draggable">
	<slot></slot>
</section>

<svelte:window on:mouseup={onMouseUp} on:mousemove={onMouseMove} />