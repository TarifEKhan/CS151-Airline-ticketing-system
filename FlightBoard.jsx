import { useState } from 'react';
import { Modal } from './Modal';
import { cities } from '../constants/FlightData';

export const FlightBoard = () => {
   const [selectedFrom, setSelectedFrom] = useState('');
   const [selectedTo, setSelectedTo] = useState('');
   const [isModalOpen, setIsModalOpen] = useState(false);

   const handleCheckTicket = () => {
      setIsModalOpen(true);
   };
   
   return (
      <div className="container_board">
         <table>
            {/* Table Header */}
            <thead>
               <tr>
                  <th>From</th>
                  <th>To</th>
                  <th>Tickets</th>
               </tr>
            </thead>

            {/* Table Body */}
            <tbody>
               {cities.map((fromCity) => (
                  <tr key={fromCity}>
                     {/* From City */}
                     <td>{fromCity}</td>

                     {/* To City */}
                     <td>
                        <select
                           onChange={(e) => {
                              setSelectedTo(e.target.value);
                              setSelectedFrom(fromCity);
                           }}
                        >
                           <option>Choose city</option>
                           {cities
                              .filter((toCity) => toCity !== fromCity)
                              .map((toCity) => (
                                 <option key={toCity} value={toCity}>
                                    {toCity}
                                 </option>
                           ))}
                        </select>
                     </td>
                     {/* Ticket Button */}
                     <td>
                        <button
                           className="btn_long"
                           onClick={handleCheckTicket}>
                           View Tickets
                        </button>
                     </td>
                  </tr>
               ))}
            </tbody>
         </table>


         {isModalOpen && (
            <Modal
               fromCity={selectedFrom}
               toCity={selectedTo}
               onClose={() => setIsModalOpen(false)}
            />
         )}
      </div>
   )
}



