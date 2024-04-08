class LabelInput extends HTMLElement {
    connectedCallback() {
        const id = this.getAttribute('i');
        const tipo = this.getAttribute('tipo') ?? 'text';
        const etiqueta = this.getAttribute('etiqueta') ?? id;
        const minimo = this.getAttribute('minimo');
        const longitudMinima = this.getAttribute('longitud-minima');
        const textoError = this.getAttribute('texto-error');
        
        let decimales = this.getAttribute('decimales');
        let atributos = this.getAttribute('atributos') ?? '';
        
        let type = tipo;

        let html = `
        <div class="row mb-3">`;

        if(tipo !== 'btn') {
            html += `<label for="${id}" class="col-sm-2 col-form-label">${etiqueta}</label>`;
        }
        
        if(tipo === 'btn') {
            html += `<div class="offset-sm-2 col-sm">`;
            html += `<button class="btn btn-primary">${etiqueta}</button></div></div>`;

            this.innerHTML = html;

            return;
        }

        html += `<div class="col-sm">`;

        
        if(tipo === 'euro') {
            html += `<div class="input-group mb-3">`;
            type = 'number';
            
            if(!decimales) {
                decimales = 2;
            }
        }

        if(decimales) {
            atributos += ` step="${decimalesAStep(decimales)}" `;
        }

        if(longitudMinima) {
            atributos += ` minlength="${longitudMinima}" `;
        }

        if(minimo) {
            atributos += ` min="${minimo}" `;
        }

        html += `<input ${atributos} type="${type}" class="form-control" id="${id}">`;

        if(tipo === 'euro') {
            html += `<span class="input-group-text">€</span>`;
        }

        html += textoError ? '<div class="invalid-feedback">' + textoError + '</div>' : '';

        if(tipo === 'euro') {
            html += `</div>`
        }

        html += `</div>
        </div>`;

        console.log(html);

        this.innerHTML = html;
    }
}

customElements.define('jl-labelinput', LabelInput);

function decimalesAStep(decimales) {
    return Math.pow(10, -decimales);
}