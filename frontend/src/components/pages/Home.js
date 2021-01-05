import React from 'react';
import '../../App.css';
import Cards from '../Cards';
import HeroSection from '../HeroSection';
import Footer from '../Footer';
import Shelters from '../pages/Shelters'

function Home() {
  return (
    <>
      <HeroSection />
      <Shelters />
      <Footer />
    </>
  );
}

export default Home;
