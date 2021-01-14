import React from 'react';
import '../App.css';
import './InfoSection.css';


function InfoSection() {
  return (
    <div className='info-container'>
      <h1>Tko smo mi?</h1>
      <p1>Naš glavni zadatak je povezati udruge za životinje s građanima 
          koji imaju želju i vrijeme za šetanje pasa, te time povećati izglede udomljavanja pasa, 
          i psihološkog efekta dobrobiti socijalizacije za psa i za čovjeka. </p1>
        <p2>Broj nezbrinutih životinja raste u Hrvatskoj, te je procijenjeno kako ima 10.000 napuštenih životinja. 
          Skloništa i udruge za nezbrinute životinje spašavaju ranjene i nezbrinute životinje, te žele potaknuti 
          građane na angažiranost, pomoć pri brizi za životinje i za njihovo udomljavanje.  </p2>
    </div>
  );
}

export default InfoSection;