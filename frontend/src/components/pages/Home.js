import React from 'react';
import '../../App.css';
import HeroSection from '../HeroSection';
import Footer from '../Footer';
import InfoSection from '../InfoSection';
import Shelters from '../pages/Shelters'

function Home() {
  return (
    <>
      <HeroSection />
      <InfoSection />
      <Shelters />
      <Footer />
    </>
  );
}

export default Home;
