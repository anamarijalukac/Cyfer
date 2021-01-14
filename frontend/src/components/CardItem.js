import React from 'react';
import { Link } from 'react-router-dom';

function CardItem(props) {
  return (
    <>
      <li className='cards__item'>
        <Link className='cards__item__link' to={props.path}>
          <figure className='cards__item__pic-wrap'  data-category={props.label}>
            <img
              className='cards__item__img'
              alt='Dog Image'
              src={props.src}
            />
          </figure>
          <div className='cards__item__info'>
            {props.needy && <h5 className='cards__item__text' style={{color: '#ee5a6f'}}>{props.text}</h5>}
            {!props.needy && <h5 className='cards__item__text'>{props.text}</h5>}
          </div>
        </Link>
      </li>
    </>
  );
}

export default CardItem;
