class TarjetaProducto extends HTMLElement {
  connectedCallback() {
    const foto = this.getAttribute('foto');
    const nombre = this.getAttribute('nombre');
    const precio = this.getAttribute('precio');

    this.innerHTML = `<div class="col">
        <div class="card h-100">
          <img src="${foto}" class="card-img-top" alt="${nombre}">
          <div class="card-body">
            <h5 class="card-title">${nombre}</h5>
            <p class="card-text">${precio}</p>
          </div>
        </div>
      </div>`;
  }
}

customElements.define('jl-tarjeta-producto', TarjetaProducto)