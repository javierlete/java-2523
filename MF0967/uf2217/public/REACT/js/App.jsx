function App() {
    const p = { id: 1, nombre: 'iPhone', precio: 1000 };
    const productos = [
        { id: 1, nombre: 'iPhone', precio: 1000 },
        { id: 2, nombre: 'iPad', precio: 2000 },
        { id: 3, nombre: 'MacBook',precio: 3000 }
    ];
    return (
        <>
            <Componente inicial={10}></Componente>
            <FichaProducto producto={p}></FichaProducto>
            <ListadoProductos productos={productos}></ListadoProductos>
            <ListadoProductosSentenciaControl productos={productos}></ListadoProductosSentenciaControl>
        </>
    );
}

function Componente({ inicial }) {
    const [count, setCount] = React.useState(inicial || 0);

    return (
        <>
            <button onClick={() => setCount(count + 1)}>Haz clic</button>
            <br />
            <div id="count_elem">Contador: {count}</div>
        </>
    );
}

function ListadoProductosSentenciaControl({ productos }) {
    let fichas = [];

    for(let p of productos) {
        if(p.precio >= 2000) {
            const ficha = <li><FichaProducto producto={p}></FichaProducto></li>;
            fichas.push(ficha);
        }
    }

    return <ul>{fichas}</ul>;
}

function ListadoProductos({ productos }) {
    return <ul>
        {productos.map(producto => 
            producto.precio >= 2000 && <li><FichaProducto producto={producto}></FichaProducto></li>)
        }
    </ul>;
}

function FichaProducto({ producto }) {
    return (
        <div>
            <h3>{producto.nombre}</h3>
            <p>Precio: {producto.precio}</p>
        </div>
    );
}