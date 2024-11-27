import React, { useEffect, useState } from 'react';
import './App.css';

function App() {
    const [produtos, setProdutos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProdutos = async () => {
            try {
                const response = await fetch('http://localhost:8080/produtos');
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                const data = await response.json();
                setProdutos(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchProdutos();
    }, []);

    return (
        <div>
            <header className="header">
                <img src="/logo Restaurante.png" alt="Logo" />
                <nav>
                    <ul className="nav">
                        <li><a href="#" className="link">Home</a></li>
                        <li><a href="#" className="link">Sobre</a></li>
                        <li><a href="#" className="link">Serviços</a></li>
                        <li><a href="#" className="link">Contato</a></li>
                    </ul>
                </nav>
            </header>
            <main className="main">
                <div className="card-container">
                    {loading ? (
                        <p>Loading...</p>
                    ) : error ? (
                        <p>Error: {error}</p>
                    ) : (
                        produtos.map(produto => (
                            <Card 
                                key={produto.id} 
                                title={produto.titulo} 
                                description={produto.descricao} 
                                price={produto.preco} 
                            />
                        ))
                    )}
                </div>
            </main>

            {/* Footer Component */}
            <Footer />
        </div>
    );
}

function Card({ title, description, price }) {
    return (
        <div className="card">
            <h3>{title}</h3>
            <p>{description}</p>
            <p><strong>Preço:</strong> R${price.toFixed(2)}</p> {/* Formatação do preço */}
            <button className="buy-button">Comprar</button>
        </div>
    );
}

// Footer Component
function Footer() {
    return (
        <footer className="footer">
            <div className="footer-content">
                <div className="footer-section">
                    <h4>Fillintro</h4>
                    <p>Sabores autênticos da Itália, onde cada prato é uma obra de arte.</p>
                </div>
                <div className="footer-section">
                    <h4>Sobre nós</h4>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Sobre</a></li>
                        <li><a href="#">Serviços</a></li>
                        <li><a href="#">Contato</a></li>
                    </ul>
                </div>
                <div className="footer-section">
                    <h4>Contato</h4>
                    <p>Email: contact@website.com</p>
                    <p>Phone: +1 234 567 890</p>
                </div>
            </div>
            <div className="footer-bottom">
                <p>&copy; 2024 Fillintro. Todos os Direitos Reservados.</p>
            </div>
        </footer>
    );
}

export default App;
