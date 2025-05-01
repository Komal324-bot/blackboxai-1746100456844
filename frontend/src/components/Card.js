import React from 'react';

function Card({ card }) {
  return (
    <div className="bg-white rounded shadow p-2">
      <h4 className="font-medium">{card.title}</h4>
      {card.description && <p className="text-sm text-gray-600">{card.description}</p>}
    </div>
  );
}

export default Card;
