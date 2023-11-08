import './App.css';
import { Link, BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import Admin from './components/Admin';
import Edit from './components/admin/Edit';
import Delete from './components/admin/Delete';
import Add from './components/admin/Add';
import Detail from './components/Detail';
function App() {
  return (
    <BrowserRouter>
      <div>
        <header className="header">
          <div className="header--content">
            <span className='nameAuthor'>Denali</span>
            <ul className='title'>
              <li className='title--item'>
                <Link className='title--item__link' to="/">Home</Link>
              </li>
              <li className='title--item'>
                <Link className='title--item__link' to="/about">About</Link>
              </li>
              <li className='title--item'>
                <Link className='title--item__link' to="/contact">Contact</Link>
              </li>
              <li className='title--item'>
                <Link className='title--item__link' to="/admin">Admin</Link>
              </li>
            </ul>
          </div>
        </header>
        <Routes>
          <Route path="/" element={<Home />}  />
          <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/edit/:id" element={<Edit />}  />
          <Route path ="/delete/:id" element={<Delete/>} />
          <Route path ="/add" element={<Add/>} />
          <Route path ="/blog/:id" element={<Detail/>} />

        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
