import './App.css';
import { Link, BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import Admin from './components/Admin';
import Edit from './components/admin/Edit';
import Delete from './components/admin/Delete';
import Add from './components/admin/Add';
import Detail from './components/Detail';
import { useNavigate } from 'react-router-dom'; // Để thực hiện redirect
import axios from 'axios'; // Thêm axios để gọi API

function App() {

  // Hàm xử lý khi nhấn nút Login
  const handleLogin = async () => {
    try {
      const response = await axios.get('https://stunning-meme-9xrx4q6r6gw3x7q4-8080.app.github.dev'); // URL backend
      const redirectUrl = response.data.redirectUrl;

      // Redirect đến URL nhận được từ backend
      window.location.href = redirectUrl;
    } catch (error) {
      console.error('Error during login:', error);
      alert('Login failed. Please try again.');
    }
  };

  return (
    <BrowserRouter>
      <div>
        <header className="header">
          <div className="header--content">
            <span className="nameAuthor">Denali</span>
            <ul className="title">
              <li className="title--item">
                <Link className="title--item__link" to="/">Home</Link>
              </li>
              <li className="title--item">
                <Link className="title--item__link" to="/about">About</Link>
              </li>
              <li className="title--item">
                <Link className="title--item__link" to="/contact">Contact</Link>
              </li>
              <li className="title--item">
                <Link className="title--item__link" to="/admin">Admin</Link>
              </li>
            </ul>
            <button className="login-button" onClick={handleLogin}>
              Login
            </button>
          </div>
        </header>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/edit/:id" element={<Edit />} />
          <Route path="/delete/:id" element={<Delete />} />
          <Route path="/add" element={<Add />} />
          <Route path="/blog/:id" element={<Detail />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
