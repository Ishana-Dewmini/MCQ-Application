import * as React from 'react';
import CircularProgress from '@mui/material/CircularProgress';
import Box from '@mui/material/Box';
import './Loading.scss';

const LoadingAnim = (props) => {
  const { showAnim } = props;

  return (
    <div className="loadingContainer" style={showAnim ? { display: 'flex' } : { display: 'none' }}>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <CircularProgress color='inherit' size={80} /> {/* Adjust the size as needed */}
        <span>Loading . . .</span>
      </Box>
    </div>
  );
}

export default LoadingAnim;


