package pro.upchain.wallet.ui.activity;

import static pro.upchain.wallet.C.ETHEREUM_MAIN_NETWORK_NAME;
import static pro.upchain.wallet.C.KOVAN_NETWORK_NAME;
import static pro.upchain.wallet.C.LOCAL_DEV_NETWORK_NAME;
import static pro.upchain.wallet.C.ROPSTEN_NETWORK_NAME;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;
import pro.upchain.wallet.R;
import pro.upchain.wallet.UpChainWalletApp;
import pro.upchain.wallet.base.BaseActivity;
import pro.upchain.wallet.entity.NetworkInfo;
import pro.upchain.wallet.repository.EthereumNetworkRepository;

/**
 * Created by Tiny 熊 @ Upchain.pro
 * WeiXin: xlbxiong
 */


public class NetSettingActivity extends BaseActivity implements View.OnClickListener {


    EthereumNetworkRepository ethereumNetworkRepository;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_btn)
    TextView tvBtn;
    @BindView(R.id.rl_btn)
    LinearLayout rlBtn;

    @BindView(R.id.iv_mainnet)
    ImageView ivMainnet;

    @BindView(R.id.iv_kovan)
    ImageView ivKovan;

    @BindView(R.id.iv_ropsten)
    ImageView ivRopsten;


    @BindView(R.id.iv_local_dev)
    ImageView ivLocalDev;

    private String networkName;
    private LinearLayout llyBack;
    private TextView ivBtn;
    private Toolbar commonToolbar;
    private RelativeLayout rlMainnet;
    private RelativeLayout rlKovan;
    private RelativeLayout rlRopsten;
    private RelativeLayout rlLocalDev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void initView() {

        tvTitle = findViewById(R.id.tv_title);
        llyBack = findViewById(R.id.lly_back);
        ivBtn = findViewById(R.id.iv_btn);
        rlBtn = findViewById(R.id.rl_btn);
        commonToolbar = findViewById(R.id.common_toolbar);
        ivMainnet = findViewById(R.id.iv_mainnet);
        rlMainnet = findViewById(R.id.rl_mainnet);
        ivKovan = findViewById(R.id.iv_kovan);
        rlKovan = findViewById(R.id.rl_kovan);
        ivRopsten = findViewById(R.id.iv_ropsten);
        rlRopsten = findViewById(R.id.rl_ropsten);
        ivLocalDev = findViewById(R.id.iv_local_dev);
        rlLocalDev = findViewById(R.id.rl_local_dev);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_net_setting;
    }

    @Override
    public void initToolBar() {
        tvTitle.setText(R.string.system_setting_net);
        rlBtn.setVisibility(View.VISIBLE);
        tvBtn.setText(R.string.language_setting_save);
    }

    @Override
    public void initDatas() {
        ethereumNetworkRepository = UpChainWalletApp.repositoryFactory().ethereumNetworkRepository;

        networkName = ethereumNetworkRepository.getDefaultNetwork().name;

        if (ETHEREUM_MAIN_NETWORK_NAME.equals(networkName)) {
            ivMainnet.setVisibility(View.VISIBLE);
            ivKovan.setVisibility(View.GONE);
            ivRopsten.setVisibility(View.GONE);
            ivLocalDev.setVisibility(View.GONE);
        } else if (KOVAN_NETWORK_NAME.equals(networkName)) {
            ivMainnet.setVisibility(View.GONE);
            ivKovan.setVisibility(View.VISIBLE);
            ivRopsten.setVisibility(View.GONE);
            ivLocalDev.setVisibility(View.GONE);
        } else if (ROPSTEN_NETWORK_NAME.equals(networkName)) {
            ivMainnet.setVisibility(View.GONE);
            ivKovan.setVisibility(View.GONE);
            ivRopsten.setVisibility(View.VISIBLE);
            ivLocalDev.setVisibility(View.GONE);
        } else if (LOCAL_DEV_NETWORK_NAME.equals(networkName)) {
            ivMainnet.setVisibility(View.GONE);
            ivKovan.setVisibility(View.GONE);
            ivRopsten.setVisibility(View.GONE);
            ivLocalDev.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void configViews() {
        rlBtn.setOnClickListener(this);
        rlMainnet.setOnClickListener(this);
        rlKovan.setOnClickListener(this);
        rlRopsten.setOnClickListener(this);
        rlLocalDev.setOnClickListener(this);
        rlBtn.setOnClickListener(this);
    }

    @OnClick({R.id.rl_mainnet, R.id.rl_kovan, R.id.rl_ropsten, R.id.rl_local_dev, R.id.rl_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mainnet:
                networkName = ETHEREUM_MAIN_NETWORK_NAME;
                ivMainnet.setVisibility(View.VISIBLE);
                ivKovan.setVisibility(View.GONE);
                ivRopsten.setVisibility(View.GONE);
                ivLocalDev.setVisibility(View.GONE);
                break;
            case R.id.rl_kovan:
                networkName = KOVAN_NETWORK_NAME;
                ivMainnet.setVisibility(View.GONE);
                ivKovan.setVisibility(View.VISIBLE);
                ivRopsten.setVisibility(View.GONE);
                ivLocalDev.setVisibility(View.GONE);
                break;
            case R.id.rl_ropsten:
                networkName = ROPSTEN_NETWORK_NAME;
                ivMainnet.setVisibility(View.GONE);
                ivKovan.setVisibility(View.GONE);
                ivRopsten.setVisibility(View.VISIBLE);
                ivLocalDev.setVisibility(View.GONE);

                break;
            case R.id.rl_local_dev:
                networkName = LOCAL_DEV_NETWORK_NAME;
                ivMainnet.setVisibility(View.GONE);
                ivKovan.setVisibility(View.GONE);
                ivRopsten.setVisibility(View.GONE);
                ivLocalDev.setVisibility(View.VISIBLE);

                break;
            case R.id.rl_btn:// 设置语言并保存
//                SharedPreferencesUtil.getInstance().putString("pref_rpcServer",networkName );

                NetworkInfo[] networks = ethereumNetworkRepository.getAvailableNetworkList();
                for (NetworkInfo networkInfo : networks) {
                    if (networkInfo.name.equals(networkName)) {
                        ethereumNetworkRepository.setDefaultNetworkInfo(networkInfo);
                    }
                }

                finish();
                break;
        }
    }
}
